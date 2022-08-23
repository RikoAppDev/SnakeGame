package riko.dev.snake.presentation.features.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import riko.dev.snake.domain.game.Game
import riko.dev.snake.domain.game.GameState
import riko.dev.snake.domain.game.Move
import riko.dev.snake.R
import riko.dev.snake.presentation.navigation.Screen
import riko.dev.snake.presentation.ui.theme.*

@Composable
fun GameScreen(game: Game, navController: NavController) {
    val state = game.state.collectAsState()
    LaunchedEffect(key1 = true) {
        game.reinitialize()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TopAppBar(
                title = { Score(state.value) },
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

            Board(state.value)

            if (state.value.running)
                Controls {
                    game.move = it
                }
            else
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    StartButton(game = game)
                }

            if (state.value.gameOver) {
                navController.navigate(Screen.GameOverScreen.withArgs(state.value.snake.size.toString()))
                game.gameOver()
            }
        }
    }
}

@Composable
fun StartButton(game: Game) {
    Card(
        elevation = 8.dp,
        backgroundColor = Brown,
        shape = RoundedCornerShape(50),
        modifier = Modifier.size(250.dp)
    ) {
        Box(modifier = Modifier
            .clickable {
                game.start()
            }
            .padding(12.dp),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = "Start",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Composable
fun Controls(onDirectionChange: (Move) -> Unit) {
    val buttonSize = Modifier.size(90.dp)
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { onDirectionChange(Move.MoveUp) }, modifier = buttonSize) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "up",
                modifier = Modifier.scale(2f),
                tint = MaterialTheme.colors.onBackground
            )
        }
        Row {
            Button(onClick = { onDirectionChange(Move.MoveLeft) }, modifier = buttonSize) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "left",
                    modifier = Modifier.scale(2f),
                    tint = MaterialTheme.colors.onBackground
                )
            }
            Spacer(modifier = buttonSize)
            Button(onClick = { onDirectionChange(Move.MoveRight) }, modifier = buttonSize) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "right",
                    modifier = Modifier.scale(2f),
                    tint = MaterialTheme.colors.onBackground
                )
            }
        }
        Button(onClick = { onDirectionChange(Move.MoveDown) }, modifier = buttonSize) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "down",
                modifier = Modifier.scale(2f),
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Composable
fun Board(state: GameState) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .border(4.dp, Grey)
            .background(Green400)
            .padding(4.dp)
    ) {
        BoxWithConstraints {
            val tileSize = maxWidth / Game.BOARD_SIZE

            Box(
                modifier = Modifier
                    .size(maxWidth)
            )

            Box(
                modifier = Modifier
                    .offset(
                        x = tileSize * state.food.first,
                        y = tileSize * state.food.second
                    )
                    .size(tileSize)
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_apple),
                    contentDescription = null,
                    tint = Fruit,
                    modifier = Modifier.size(tileSize)
                )
            }

            state.snake.forEach {
                if (it == state.snake.first())
                    Box(
                        modifier = Modifier
                            .offset(x = tileSize * it.first, y = tileSize * it.second)
                            .size(tileSize)
                            .background(
                                Color.Transparent, Shapes.small
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.snake_head),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(tileSize)
                        )
                    }
                else {
                    Box(
                        modifier = Modifier
                            .offset(x = tileSize * it.first, y = tileSize * it.second)
                            .size(tileSize)
                            .padding(0.5.dp)
                            .background(
                                DarkGreen900, Shapes.small
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun Score(state: GameState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(end = 64.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Score:",
            fontSize = 24.sp,
            textAlign = TextAlign.Left
        )
        Text(
            text = state.snake.size.toString(),
            fontSize = 24.sp,
            textAlign = TextAlign.Right
        )
    }
}
