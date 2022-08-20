package riko.dev.snake.data.data_source

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import riko.dev.snake.common.Constants
import riko.dev.snake.domain.model.PlayerScore

class FirestoreRepository {
    private val mFirestore = Firebase.firestore
    private val leaderboardRef = mFirestore.collection(Constants.LEADERBOARD)

    suspend fun getLeaderboardData(): MutableList<PlayerScore> {
        val leaderboardData = mutableListOf(PlayerScore())

        leaderboardRef
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val playerScore: PlayerScore = document.toObject()
                    leaderboardData.add(playerScore)
                    Log.d(Constants.FIRESTORE, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(Constants.FIRESTORE, "Error getting documents: ", exception)
            }
            .await()

        leaderboardData.sortByDescending { it.score }

        return leaderboardData
    }
}
