package riko.dev.snake.presentation.features.leaderboard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import riko.dev.snake.common.Resource
import riko.dev.snake.domain.use_case.get_leaderboard.GetLeaderboardUseCase

class LeaderboardViewModel : ViewModel() {
    private val getLeaderboardUseCase = GetLeaderboardUseCase()

    private val _state = mutableStateOf(LeaderboardState())
    val state: State<LeaderboardState> = _state

    init {
        viewModelScope.launch {
            getLeaderboardUseCase().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = LeaderboardState(leaderboardData = result.data!!)
                    }
                    is Resource.Error -> {
                        _state.value =
                            LeaderboardState(
                                error = result.message ?: "An unexpected error occurred"
                            )
                    }
                    is Resource.Loading -> {
                        _state.value = LeaderboardState(isLoading = true)
                    }
                }
            }
        }
    }
}
