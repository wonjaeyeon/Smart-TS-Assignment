package com.kikii.smarttsassignment.ui.feature.settings.sub

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@Composable
fun AccountSettingsScreen (
    modifier: Modifier = Modifier,
    navController: NavHostController,

    ){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        // Row for Back Button and Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Space between the button and title
        ) {
            // Back Button
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                    contentDescription = "settings",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            // Title Text
            Text(
                text = stringResource(id = R.string.settings_account_title),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f) // Center-align the text in the row
            )

            Spacer(modifier = Modifier.width(48.dp)) // Placeholder space to align text centrally
        }

        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(id = R.string.settings_account_subtitle),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.outline
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountSettingsScreen() {
    // Mock NavHostController
    val navController = rememberNavController()

    AccountSettingsScreen(
        modifier = Modifier,
        navController = navController
    )
}