package riko.dev.snake.presentation.features.leaderboard

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import riko.dev.snake.presentation.features.leaderboard.components.PlayerScoreItem
import riko.dev.snake.presentation.ui.theme.Grey

@Composable
fun LeaderboardScreen(navController: NavController) {
    val leaderboardViewModel: LeaderboardViewModel = viewModel()
    val state = leaderboardViewModel.state.value

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
                        items(state.leaderboardData) {
                            if (it.name != "" && it.score != 0)
                                PlayerScoreItem(
                                    modifier = Modifier,
                                    position = state.leaderboardData.indexOf(it) + 1,
                                    name = it.name,
                                    score = it.score
                                )
                        }
                    }
                }
                if (state.error != null) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = state.error,
                            color = MaterialTheme.colors.error,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        )
                    }
                }
                if (state.isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = MaterialTheme.colors.secondary)
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
            text = "#",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(.2f)
        )
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
