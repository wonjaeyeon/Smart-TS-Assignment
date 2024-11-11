package com.kikii.smarttsassignment.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.window.layout.DisplayFeature
import com.kikii.smarttsassignment.ui.feature.empty.EmptyComingSoon
import com.kikii.smarttsassignment.ui.feature.settings.FirstSettingScreen
import com.kikii.smarttsassignment.ui.window.ReplyContentType
import com.kikii.smarttsassignment.ui.window.ReplyNavigationType

@Composable
fun ReplyNavHost(
    navController: NavHostController,
    contentType: ReplyContentType,
    displayFeatures: List<DisplayFeature>,
    navigationType: ReplyNavigationType,
    modifier: Modifier = Modifier,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.Router,
    ) {
        composable<Route.Router> {
            EmptyComingSoon()
        }

        composable<Route.Dispatcher> {
            EmptyComingSoon()
        }

        composable<Route.Settings> {
            FirstSettingScreen()
        }

    }
}
