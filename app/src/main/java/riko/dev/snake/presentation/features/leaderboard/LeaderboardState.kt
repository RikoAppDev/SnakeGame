package riko.dev.snake.presentation.features.leaderboard

import riko.dev.snake.domain.model.PlayerScore

data class LeaderboardState(
    var isLoading: Boolean = false,
    var leaderboardData: List<PlayerScore> = emptyList(),
    val error: String? = null
)