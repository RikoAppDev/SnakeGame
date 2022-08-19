package riko.dev.snake.domain.game

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random

class Game(private val scope: CoroutineScope) {
    private var initialMove = Move.values().random()
    var initialState = GameState(
        food = Pair(Random.nextInt(BOARD_SIZE), Random.nextInt(BOARD_SIZE)),
        snake = arrayListOf(Pair(BOARD_SIZE / 2, BOARD_SIZE / 2)).apply {
            for (i in 1 until SNAKE_LENGTH) {
                when {
                    initialMove.coordinates.first == 1 -> {
                        add(Pair(BOARD_SIZE / 2 - i, BOARD_SIZE / 2))
                    }
                    initialMove.coordinates.first == -1 -> {
                        add(Pair(BOARD_SIZE / 2 + i, BOARD_SIZE / 2))
                    }
                    initialMove.coordinates.second == 1 -> {
                        add(Pair(BOARD_SIZE / 2, BOARD_SIZE / 2 - i))
                    }
                    initialMove.coordinates.second == -1 -> {
                        add(Pair(BOARD_SIZE / 2, BOARD_SIZE / 2 + i))
                    }
                }
            }
        },
        running = false
    )

    private val mutex = Mutex()
    private val _state = MutableStateFlow(
        GameState(
            food = Pair(Random.nextInt(BOARD_SIZE), Random.nextInt(BOARD_SIZE)),
            snake = arrayListOf(Pair(BOARD_SIZE / 2, BOARD_SIZE / 2)).apply {
                for (i in 1 until SNAKE_LENGTH) {
                    when {
                        initialMove.coordinates.first == 1 -> {
                            add(Pair(BOARD_SIZE / 2 - i, BOARD_SIZE / 2))
                        }
                        initialMove.coordinates.first == -1 -> {
                            add(Pair(BOARD_SIZE / 2 + i, BOARD_SIZE / 2))
                        }
                        initialMove.coordinates.second == 1 -> {
                            add(Pair(BOARD_SIZE / 2, BOARD_SIZE / 2 - i))
                        }
                        initialMove.coordinates.second == -1 -> {
                            add(Pair(BOARD_SIZE / 2, BOARD_SIZE / 2 + i))
                        }
                    }
                }
            },
            running = false
        )
    )
    val state = _state.asStateFlow()

    var move = initialMove
        set(value) {
            scope.launch {
                mutex.withLock {
                    field = value
                }
            }
        }

    fun start() {
        scope.launch {
            var snakeLength = SNAKE_LENGTH
            _state.value.running = true

            while (_state.value.running) {
                delay(150)
                _state.update {
                    val newPosition = it.snake.first().let { position ->
                        mutex.withLock {
                            var x = (position.first + move.coordinates.first) % BOARD_SIZE
                            var y = (position.second + move.coordinates.second) % BOARD_SIZE

                            if (x < 0)
                                x = BOARD_SIZE - (-x)
                            if (y < 0)
                                y = BOARD_SIZE - (-y)

                            Pair(x, y)
                        }
                    }

                    if (newPosition == it.food)
                        snakeLength++

                    if (it.snake.contains(newPosition)) {
                        snakeLength = SNAKE_LENGTH
                        end()
                        _state.value.running = false
                    }

                    it.copy(
                        food = if (newPosition == it.food)
                            Pair(
                                Random.nextInt(BOARD_SIZE),
                                Random.nextInt(BOARD_SIZE)
                            ) else it.food,
                        snake = listOf(newPosition) + it.snake.take(snakeLength - 1),
                    )
                }
            }
        }
    }

    private fun end() {
        _state.update {
            it.copy(
                food = initialState.food,
                snake = initialState.snake,
                running = initialState.running
            )
        }
        Log.d("TEST", "end: $move")
        Log.d("TEST", "end: ${state.value}")
    }

    companion object {
        const val BOARD_SIZE = 20
        const val SNAKE_LENGTH = 3
    }
}