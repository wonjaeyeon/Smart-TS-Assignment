package com.kikii.smarttsassignment.ui

import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature
import com.kikii.smarttsassignment.ui.navigation.ReplyNavHost
import com.kikii.smarttsassignment.ui.navigation.ReplyNavigationActions
import com.kikii.smarttsassignment.ui.navigation.components.ReplyNavigationWrapper
import com.kikii.smarttsassignment.ui.navigation.toReplyNavType
import com.kikii.smarttsassignment.ui.window.DevicePosture
import com.kikii.smarttsassignment.ui.window.ReplyContentType
import com.kikii.smarttsassignment.ui.window.isBookPosture
import com.kikii.smarttsassignment.ui.window.isSeparating

@Composable
fun SmartTsApp(
    windowSize: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
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
        ReplyNavigationActions(navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Surface {
        ReplyNavigationWrapper(
            currentDestination = currentDestination,
            navigateToTopLevelDestination = navigationActions::navigateTo
        ) {
            ReplyNavHost(
                navController = navController,
                contentType = contentType,
                displayFeatures = displayFeatures,
                navigationType = navSuiteType.toReplyNavType(),
            )
        }
    }
}