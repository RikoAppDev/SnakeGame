package riko.dev.snake.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Brown,
    primaryVariant = Grey,
    secondary = Green400,
    background = Green900,
    onBackground = Color.White

)

private val LightColorPalette = lightColors(
    primary = Brown,
    primaryVariant = Grey,
    secondary = Green400,
    background = Green900,
    onBackground = Color.White


    /* Other default colors to override
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun SnakeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}