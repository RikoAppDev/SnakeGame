package riko.dev.snake.presentation.features.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import riko.dev.snake.presentation.ui.theme.Brown
import riko.dev.snake.presentation.ui.theme.Green400
import riko.dev.snake.presentation.ui.theme.Grey

@Composable
fun SettingsScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var music by remember { mutableStateOf(true) }
    var sounds by remember { mutableStateOf(true) }
    var vibrations by remember { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            TopAppBar(
                title = { Text(text = "Settings", fontSize = 24.sp) },
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
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Player name:", fontSize = 24.sp)
                        Spacer(modifier = Modifier.width(12.dp))
                        TextField(
                            value = name,
                            onValueChange = { name = it },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(onDone = {
                                focusManager.clearFocus()
                            }),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Unspecified,
                                focusedIndicatorColor = Color.Unspecified,
                                unfocusedIndicatorColor = Color.Unspecified,
                                textColor = MaterialTheme.colors.onBackground
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Green400, shape = RoundedCornerShape(50))
                                .border(2.dp, Brown, shape = RoundedCornerShape(50)),
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Music:", fontSize = 24.sp)
                        Switch(
                            checked = music,
                            onCheckedChange = { music = !music },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Brown,
                                checkedTrackColor = Brown
                            )
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Sounds:", fontSize = 24.sp)
                        Switch(
                            checked = sounds,
                            onCheckedChange = { sounds = !sounds },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Brown,
                                checkedTrackColor = Brown
                            )
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Vibrations:", fontSize = 24.sp)
                        Switch(
                            checked = vibrations,
                            onCheckedChange = { vibrations = !vibrations },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Brown,
                                checkedTrackColor = Brown
                            )
                        )
                    }
                }
            }
        }
    }
}