package riko.dev.snake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import riko.dev.snake.domain.game.Game
import riko.dev.snake.presentation.navigation.nav_graph.NavGraph
import riko.dev.snake.presentation.ui.theme.SnakeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val game = Game(lifecycleScope)

        setContent {
            SnakeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavGraph(game = game)
                }
            }
        }
    }
}