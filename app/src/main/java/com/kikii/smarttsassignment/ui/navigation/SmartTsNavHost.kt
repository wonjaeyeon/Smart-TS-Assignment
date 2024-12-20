package com.kikii.smarttsassignment.ui.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.window.layout.DisplayFeature
import com.kikii.smarttsassignment.ui.feature.auth.LoginViewModel
import com.kikii.smarttsassignment.ui.feature.dispatch.DispatchScreen
import com.kikii.smarttsassignment.ui.feature.empty.EmptyComingSoon
import com.kikii.smarttsassignment.ui.feature.route.RouteScreen
import com.kikii.smarttsassignment.ui.feature.settings.SettingScreen
import com.kikii.smarttsassignment.ui.window.ReplyContentType
import com.kikii.smarttsassignment.ui.window.ReplyNavigationType

@Composable
fun SmartTsNavHost(
    navController: NavHostController,
    contentType: ReplyContentType,
    displayFeatures: List<DisplayFeature>,
    navigationType: ReplyNavigationType,
    modifier: Modifier = Modifier
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.Router,
    ) {
        composable<Route.Router> {
            RouteScreen()
        }

        composable<Route.Dispatcher> {
            DispatchScreen()
        }

        composable<Route.Settings> {
            SettingScreen()
        }

    }
}
