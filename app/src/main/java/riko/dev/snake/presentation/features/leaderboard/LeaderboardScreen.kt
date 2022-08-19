package riko.dev.snake.presentation.features.leaderboard

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import riko.dev.snake.presentation.ui.theme.Grey

@Composable
fun LeaderboardScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            TopAppBar(
                title = { Text(text = "Leaderboard", fontSize = 24.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "navigate back"
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
                    .border(4.dp, Grey)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    TableHeader()
                    LazyColumn {

                    }
                }
            }
        }
    }
}

@Composable
fun TableHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Player name",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "Score",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}
