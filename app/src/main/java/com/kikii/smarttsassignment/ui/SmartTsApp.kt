package com.kikii.smarttsassignment.ui

import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature
import com.kikii.smarttsassignment.ui.feature.auth.AuthUiState
import com.kikii.smarttsassignment.ui.feature.auth.LoginScreen
import com.kikii.smarttsassignment.ui.feature.auth.LoginViewModel
import com.kikii.smarttsassignment.ui.navigation.SmartTsNavHost
import com.kikii.smarttsassignment.ui.navigation.SmartTsNavigationActions
import com.kikii.smarttsassignment.ui.navigation.components.SmartTsNavigationWrapper
import com.kikii.smarttsassignment.ui.navigation.toReplyNavType
import com.kikii.smarttsassignment.ui.window.DevicePosture
import com.kikii.smarttsassignment.ui.window.ReplyContentType
import com.kikii.smarttsassignment.ui.window.isBookPosture
import com.kikii.smarttsassignment.ui.window.isSeparating

@Composable
fun SmartTsApp(
    windowSize: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val foldingFeature = displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull()

    val foldingDevicePosture = when {
        isBookPosture(foldingFeature) ->
            DevicePosture.BookPosture(foldingFeature.bounds)

        isSeparating(foldingFeature) ->
            DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)

        else -> DevicePosture.NormalPosture
    }

    val contentType = when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> ReplyContentType.SINGLE_PANE
        WindowWidthSizeClass.Medium -> if (foldingDevicePosture != DevicePosture.NormalPosture) {
            ReplyContentType.DUAL_PANE
        } else {
            ReplyContentType.SINGLE_PANE
        }
        WindowWidthSizeClass.Expanded -> ReplyContentType.DUAL_PANE
        else -> ReplyContentType.SINGLE_PANE
    }

    val navController = rememberNavController()

    val navigationActions = remember(navController) {
        SmartTsNavigationActions(navController)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination

    // 인증 상태를 관리하는 mutableStateOf
    val user = loginViewModel.authUiState.collectAsStateWithLifecycle()


    val isAuthenticated = if (user.value is AuthUiState.Success) {
        true
    } else {
        false
    }

    if(isAuthenticated){
    Surface {
        SmartTsNavigationWrapper(
            currentDestination = currentDestination,
            navigateToTopLevelDestination = navigationActions::navigateTo
        ) {
            SmartTsNavHost(
                navController = navController,
                contentType = contentType,
                displayFeatures = displayFeatures,
                navigationType = navSuiteType.toReplyNavType(),
            )
        }
    }}
    else{
        LoginScreen {
            //isAuthenticated = true // onLoginSuccess 콜백 호출 시 실행됨
        }
    }
}