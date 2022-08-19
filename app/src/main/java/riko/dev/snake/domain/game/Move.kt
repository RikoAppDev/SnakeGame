package riko.dev.snake.domain.game

enum class Move(val coordinates: Pair<Int, Int>) {
    MoveUp(Pair(0, -1)),
    MoveDown(Pair(0, 1)),
    MoveLeft(Pair(-1, 0)),
    MoveRight(Pair(1, 0))
}
