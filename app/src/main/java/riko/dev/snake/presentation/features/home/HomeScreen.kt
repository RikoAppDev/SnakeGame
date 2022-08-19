package riko.dev.snake.presentation.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import riko.dev.snake.R
import riko.dev.snake.presentation.navigation.Screen
import riko.dev.snake.presentation.ui.theme.Brown
import riko.dev.snake.presentation.ui.theme.Grey

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .border(4.dp, Grey),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 84.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "logo",
                modifier = Modifier
                    .scale(2f)
                    .size(200.dp)
            )
            Spacer(modifier = Modifier.height(44.dp))
            Card(
                elevation = 8.dp,
                backgroundColor = Brown,
                shape = RoundedCornerShape(50),
                modifier = Modifier.width(250.dp)
            ) {
                Box(modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.GameScreen.route)
                    }
                    .padding(12.dp),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(
                        text = "New Game",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                elevation = 8.dp,
                backgroundColor = Brown,
                shape = RoundedCornerShape(50),
                modifier = Modifier.width(250.dp)
            ) {
                Box(modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.LeaderboardScreen.route)
                    }
                    .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Leaderboard",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                elevation = 8.dp,
                backgroundColor = Brown,
                shape = RoundedCornerShape(50),
                modifier = Modifier.width(250.dp)
            ) {
                Box(modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.SettingsScreen.route)
                    }
                    .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Settings",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                elevation = 8.dp,
                backgroundColor = Brown,
                shape = RoundedCornerShape(50),
                modifier = Modifier.width(250.dp)
            ) {
                Box(modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.AboutScreen.route)
                    }
                    .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "About",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}