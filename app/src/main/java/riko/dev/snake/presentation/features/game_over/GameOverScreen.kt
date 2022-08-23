package riko.dev.snake.presentation.features.game_over

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import riko.dev.snake.presentation.navigation.Screen
import riko.dev.snake.presentation.ui.theme.Brown
import riko.dev.snake.presentation.ui.theme.Grey

@Composable
fun GameOverScreen(navController: NavController, score: Int) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            TopAppBar(
                title = { Text(text = "Back to menu", fontSize = 24.sp) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.HomeScreen.route) {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "navigate to menu"
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .border(4.dp, Grey),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "GAME\nOVER",
                        fontSize = 84.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Row {
                        Text(
                            text = "Player:",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Riko",
                            fontSize = 24.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        Text(
                            text = "Score:",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = score.toString(),
                            fontSize = 24.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Card(
                        elevation = 8.dp,
                        backgroundColor = Brown,
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.width(250.dp)
                    ) {
                        Box(modifier = Modifier
                            .clickable {
                                navController.popBackStack()
                            }
                            .padding(12.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "play again",
                                    modifier = Modifier.size(24.dp),
                                    tint = MaterialTheme.colors.onBackground
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Play again",
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colors.onBackground
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}