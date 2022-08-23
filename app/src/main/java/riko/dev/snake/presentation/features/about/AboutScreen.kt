package riko.dev.snake.presentation.features.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import riko.dev.snake.BuildConfig
import riko.dev.snake.R
import riko.dev.snake.presentation.ui.theme.Brown
import riko.dev.snake.presentation.ui.theme.Grey

@Composable
fun AboutScreen(navController: NavController) {
    val uriHandler = LocalUriHandler.current
    val sourceCodeUri = stringResource(id = R.string.github_url)

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            TopAppBar(
                title = { Text(text = "About", fontSize = 24.sp) },
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
                    .border(4.dp, Grey),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "logo",
                        modifier = Modifier
                            .scale(2f)
                            .size(200.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontSize = 84.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "v${BuildConfig.VERSION_NAME}",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = stringResource(id = R.string.about),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Card(
                        elevation = 8.dp,
                        backgroundColor = Brown,
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.width(250.dp)
                    ) {
                        Box(modifier = Modifier
                            .clickable {
                                uriHandler.openUri(sourceCodeUri)
                            }
                            .padding(12.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_github_logo),
                                    contentDescription = "github logo",
                                    modifier = Modifier.size(24.dp),
                                    tint = MaterialTheme.colors.onBackground
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Source code",
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colors.onBackground
                                )
                            }
                        }
                    }
                }
                Text(
                    text = stringResource(id = R.string.made_by),
                    fontSize = 8.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 12.dp)
                )
            }
        }
    }
}