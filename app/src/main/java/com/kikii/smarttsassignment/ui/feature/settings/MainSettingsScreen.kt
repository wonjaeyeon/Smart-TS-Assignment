package com.kikii.smarttsassignment.ui.feature.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kikii.smarttsassignment.R


@Composable
fun MainSettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController) {
// Add your settings UI here
    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(16.dp))
        // Title
        Text(
            text = stringResource(id = R.string.settings_title), // Assume "Settings" is defined here
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.Start)
        )
        // Button to navigate to Notification Settings
        Button(onClick = { navController.navigate(SettingRoute.NotificationSettings.toString()) }) {
            Text("Notification Settings")
        }
        // Button to navigate to Privacy Settings
        Button(onClick = { navController.navigate(SettingRoute.PrivacySettings.toString()) }) {
            Text("Privacy Settings")
        }
    }
}

@Preview
@Composable
fun PreviewMainSettingsScreen() {
    // Mock NavController for preview purposes
    val navController = rememberNavController()

    MainSettingsScreen(
        navController = navController
    )
}