package riko.dev.snake.domain.game

data class GameState(
    val food: Pair<Int, Int>,
    val snake: List<Pair<Int, Int>>,
    var running: Boolean,
    var gameOver: Boolean
)
