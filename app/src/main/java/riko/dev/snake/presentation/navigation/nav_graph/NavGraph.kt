package riko.dev.snake.presentation.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import riko.dev.snake.domain.game.Game
import riko.dev.snake.presentation.features.about.AboutScreen
import riko.dev.snake.presentation.features.game.GameScreen
import riko.dev.snake.presentation.features.game_over.GameOverScreen
import riko.dev.snake.presentation.features.home.HomeScreen
import riko.dev.snake.presentation.navigation.Screen
import riko.dev.snake.presentation.features.leaderboard.LeaderboardScreen
import riko.dev.snake.presentation.features.settings.SettingsScreen

@Composable
fun NavGraph(game: Game) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.GameScreen.route) {
            GameScreen(game = game, navController = navController)
        }
        composable(
            route = Screen.GameOverScreen.route + "/{score}",
            arguments = listOf(
                navArgument("score") {
                    type = NavType.IntType
                }
            )
        ) {
            GameOverScreen(
                navController = navController,
                score = it.arguments?.getInt("score")!!
            )
        }
        composable(route = Screen.LeaderboardScreen.route) {
            LeaderboardScreen(navController = navController)
        }
        composable(route = Screen.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }
        composable(route = Screen.AboutScreen.route) {
            AboutScreen(navController = navController)
        }
    }
}