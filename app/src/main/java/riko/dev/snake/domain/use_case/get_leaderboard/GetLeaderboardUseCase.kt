package riko.dev.snake.domain.use_case.get_leaderboard

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import riko.dev.snake.common.Constants
import riko.dev.snake.common.Resource
import riko.dev.snake.data.data_source.FirestoreRepository
import riko.dev.snake.domain.model.PlayerScore
import java.io.IOException

class GetLeaderboardUseCase {
    private val repository = FirestoreRepository()

    operator fun invoke(): Flow<Resource<List<PlayerScore>>> = flow {
        try {
            emit(Resource.Loading())
            val leaderboardData = repository.getLeaderboardData()
            emit(Resource.Success(leaderboardData))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        } catch (e: Exception) {
            Log.e(Constants.FIRESTORE, "Error: ${e.message}")
            emit(Resource.Error(e.message.toString()))
        }
    }
}
