import androidx.compose.animation.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@ExperimentalAnimationApi
@Composable
@Preview
fun App() {
    MaterialTheme {
        MainDisplay()
    }
}

@ExperimentalAnimationApi
@Composable
fun MainDisplay(
    slotViewModel: SlotViewModel = viewModel()
) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TimerDisplay(slotViewModel)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {slotViewModel.choose()},
        ) {
            Text(text = "Go!")
        }
    }
}


@ExperimentalAnimationApi
@Composable
fun TimerDisplay(slotViewModel: SlotViewModel = viewModel()) {
    val name by slotViewModel.person.collectAsState()
    AnimatedContent(targetState = name,
        transitionSpec = {
            slideInVertically { it } + fadeIn() with slideOutVertically { -it } + fadeOut()
        }) {
        Row(modifier = Modifier.width(500.dp),
            horizontalArrangement = Arrangement.Start) {
            Text(
                text = name,
                style = TextStyle(fontSize = MaterialTheme.typography.h1.fontSize),
                textAlign = TextAlign.Center,
                softWrap = false,
            )
        }
    }
}

@ExperimentalAnimationApi
fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
