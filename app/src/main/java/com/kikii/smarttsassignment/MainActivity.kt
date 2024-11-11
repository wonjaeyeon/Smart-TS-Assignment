package com.kikii.smarttsassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.adaptive.calculateDisplayFeatures
import com.kikii.smarttsassignment.ui.theme.SmartTsAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartTsAssignmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSize = calculateWindowSizeClass(this)
                    val displayFeatures = calculateDisplayFeatures(this)

                    SmartTsApp(
                        windowSize = windowSize,
                        displayFeatures = displayFeatures,
                        //replyHomeUIState = uiState,
//                        closeDetailScreen = {
//                            //viewModel.closeDetailScreen()
//                        },
//                        navigateToDetail = { emailId, pane ->
//                            //viewModel.setOpenedEmail(emailId, pane)
//                        },
//                        toggleSelectedEmail = { emailId ->
//                            //viewModel.toggleSelectedEmail(emailId)
//                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SmartTsAssignmentTheme {
        Greeting("Android")
    }
}