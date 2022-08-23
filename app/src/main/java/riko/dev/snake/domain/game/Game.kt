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
    private val initialMove = Move.values().random()
    private var snakeLength = SNAKE_LENGTH

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
            running = false,
            gameOver = false
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
            _state.value.running = true

            while (state.value.running) {
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
                        _state.value.gameOver = true
                        _state.value.running = false
                        Log.d("TEST", "hit: $move")
                        Log.d("TEST", "hit: ${state.value}")
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

    fun reinitialize() {
        snakeLength = SNAKE_LENGTH
        move = Move.values().random()

        _state.value = GameState(
            food = Pair(Random.nextInt(BOARD_SIZE), Random.nextInt(BOARD_SIZE)),
            snake = arrayListOf(Pair(BOARD_SIZE / 2, BOARD_SIZE / 2)).apply {
                for (i in 1 until SNAKE_LENGTH) {
                    when {
                        move.coordinates.first == 1 -> {
                            add(Pair(BOARD_SIZE / 2 - i, BOARD_SIZE / 2))
                        }
                        move.coordinates.first == -1 -> {
                            add(Pair(BOARD_SIZE / 2 + i, BOARD_SIZE / 2))
                        }
                        move.coordinates.second == 1 -> {
                            add(Pair(BOARD_SIZE / 2, BOARD_SIZE / 2 - i))
                        }
                        move.coordinates.second == -1 -> {
                            add(Pair(BOARD_SIZE / 2, BOARD_SIZE / 2 + i))
                        }
                    }
                }
            },
            running = false,
            gameOver = false
        )

        Log.d("TEST", "reinitialize: $move")
        Log.d("TEST", "reinitialize: ${state.value}")
    }

    fun gameOver() {
        _state.update {
            it.copy(
                gameOver = false
            )
        }
    }

    companion object {
        const val BOARD_SIZE = 20
        const val SNAKE_LENGTH = 3
    }
}