package com.kikii.smarttsassignment.ui.feature.settings.sub.account

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kikii.smarttsassignment.R
import com.kikii.smarttsassignment.ui.feature.auth.AuthUiState
import com.kikii.smarttsassignment.ui.feature.auth.LoginViewModel

//@Composable
//fun AccountSettingsScreen (
//    modifier: Modifier = Modifier,
//    navController: NavHostController,
//    loginViewModel: LoginViewModel = hiltViewModel()
//
//    ){
//
//    val user = loginViewModel.authUiState.collectAsStateWithLifecycle()
//
//    val userInfo = if (user.value is AuthUiState.Success) {
//        val authModel = (user.value as AuthUiState.Success).authModel
//        UserInfo(
//            loginId = authModel.loginId,
//            name = authModel.name,
//            userRoleName = authModel.userRoleName,
//            position = authModel.position,
//            companyName = authModel.companyName
//        )
//    } else {
//        UserInfo("", "", "", "", "")
//    }
//
//    val (loginId, name, userRoleName, position, companyName) = userInfo
//
//
//    Column(
//        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Spacer(modifier = Modifier.height(40.dp))
//        // Row for Back Button and Title
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 8.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween // Space between the button and title
//        ) {
//            // Back Button
//            IconButton(onClick = { navController.popBackStack() }) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
//                    contentDescription = "settings",
//                    tint = MaterialTheme.colorScheme.primary
//                )
//            }
//
//            // Title Text
//            Text(
//                text = stringResource(id = R.string.settings_account_title),
//                style = MaterialTheme.typography.titleLarge,
//                textAlign = TextAlign.Center,
//                color = MaterialTheme.colorScheme.primary,
//                modifier = Modifier.weight(1f) // Center-align the text in the row
//            )
//
//            Spacer(modifier = Modifier.width(48.dp)) // Placeholder space to align text centrally
//        }
//
//        Text(
//            modifier = Modifier.padding(horizontal = 8.dp),
//            text = stringResource(id = R.string.settings_account_subtitle),
//            style = MaterialTheme.typography.bodySmall,
//            textAlign = TextAlign.Center,
//            color = MaterialTheme.colorScheme.outline
//        )
//
//        Spacer(modifier = Modifier.height(40.dp))
//        Text(
//            text = loginId,
//            style = MaterialTheme.typography.titleMedium,
//            color = MaterialTheme.colorScheme.primary
//        )
//        Text(
//            text = userRoleName,
//            style = MaterialTheme.typography.titleMedium,
//            color = MaterialTheme.colorScheme.primary
//        )
//        Text(
//            text = name,
//            style = MaterialTheme.typography.titleMedium,
//            color = MaterialTheme.colorScheme.primary
//        )
//        Text(
//            text = position,
//            style = MaterialTheme.typography.titleMedium,
//            color = MaterialTheme.colorScheme.primary
//        )
//        Text(
//            text = companyName,
//            style = MaterialTheme.typography.titleMedium,
//            color = MaterialTheme.colorScheme.primary
//        )
//    }
//}

@Composable
fun AccountSettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val user = loginViewModel.authUiState.collectAsStateWithLifecycle()

    val userInfo = if (user.value is AuthUiState.Success) {
        val authModel = (user.value as AuthUiState.Success).authModel
        UserInfo(
            loginId = authModel.loginId,
            name = authModel.name,
            userRoleName = authModel.userRoleName,
            position = authModel.position,
            companyName = authModel.companyName
        )
    } else {
        UserInfo("", "", "", "", "")
    }

    val (loginId, name, userRoleName, position, companyName) = userInfo

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Header Row with Back Button and Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                    contentDescription = "settings",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = stringResource(id = R.string.settings_account_title),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(48.dp)) // Placeholder to align title in the center
        }

        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(id = R.string.settings_account_subtitle),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.outline
        )

        Spacer(modifier = Modifier.height(40.dp))

        // User Info
        Text(
            text = loginId,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = userRoleName,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = position,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = companyName,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )


        // Logout Button
        Button(
            onClick = {
                loginViewModel.logout(
                    onLogoutComplete = {
                        navController.popBackStack()
                    }
                )
                //navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.logout))
        }
    }
}

data class UserInfo(
    val loginId: String,
    val name: String,
    val userRoleName: String,
    val position: String,
    val companyName: String
)


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