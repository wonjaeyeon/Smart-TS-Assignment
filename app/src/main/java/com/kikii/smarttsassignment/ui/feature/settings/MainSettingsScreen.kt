package com.kikii.smarttsassignment.ui.feature.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kikii.smarttsassignment.R
import com.kikii.smarttsassignment.ui.components.list.ListItem
import com.kikii.smarttsassignment.ui.components.list.SmartTsItemList


@Composable
fun MainSettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val listItems = listOf(
        ListItem(
            id = 1,
            title = "Notification Settings",
            subtitle = "Manage your notifications",
            image = Icons.Filled.Notifications,
            navigationId = SettingRoute.NotificationSettings.toString()
        ),
        ListItem(
            id = 2,
            title = "Privacy Settings",
            subtitle = "Manage your privacy preferences",
            image = Icons.Filled.Lock,
            navigationId = SettingRoute.PrivacySettings.toString()
        ),
        ListItem(
            id = 3,
            title = "Account Settings",
            subtitle = "Manage your account preferences",
            image = Icons.Filled.Person,
            navigationId = SettingRoute.PrivacySettings.toString()
        )
    )


    // Add your settings UI here
    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(48.dp))
        // Title
        Text(
            text = stringResource(id = R.string.settings_title), // Assume "Settings" is defined here
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,

        )
//        // Button to navigate to Notification Settings
//        Button(onClick = { navController.navigate(SettingRoute.NotificationSettings.toString()) }) {
//            Text("Notification Settings")
//        }
//        // Button to navigate to Privacy Settings
//        Button(onClick = { navController.navigate(SettingRoute.PrivacySettings.toString()) }) {
//            Text("Privacy Settings")
//        }
        SmartTsItemList(listItems = listItems, navController = navController)
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