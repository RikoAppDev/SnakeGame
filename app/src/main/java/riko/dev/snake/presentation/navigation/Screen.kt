package riko.dev.snake.presentation.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "home_screen")
    object GameScreen : Screen(route = "game_screen")
    object GameOverScreen : Screen(route = "game_over_screen")
    object LeaderboardScreen : Screen(route = "highest_score_screen")
    object SettingsScreen : Screen(route = "settings_screen")
    object AboutScreen : Screen(route = "about_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
