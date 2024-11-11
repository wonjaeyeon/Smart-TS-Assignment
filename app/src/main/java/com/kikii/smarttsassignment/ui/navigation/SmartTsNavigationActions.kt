package com.kikii.smarttsassignment.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.kikii.smarttsassignment.R


class SmartTsNavigationActions(private val navController: NavHostController) {

    fun navigateTo(destination: TopLevelDestination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // re-selecting the same item
            launchSingleTop = true
            // Restore state when re-selecting a previously selected item
            restoreState = true
        }
    }
}

// used with for loop for making navigation components
val TOP_LEVEL_DESTINATIONS = listOf(
    TopLevelDestination(
        route = Route.Router,
        selectedIcon = Icons.Default.PersonPin,
        unselectedIcon = Icons.Default.PersonPin,
        iconTextId = R.string.tab_router
    ),
    TopLevelDestination(
      route = Route.Dispatcher,
        selectedIcon = Icons.Default.DirectionsBus,
        unselectedIcon = Icons.Default.DirectionsBus,
        iconTextId = R.string.tab_dispatcher
    ),
    TopLevelDestination(
        route = Route.Settings, // Add Settings to the top-level destinations
        selectedIcon = Icons.Default.Settings,
        unselectedIcon = Icons.Default.Settings,
        iconTextId = R.string.tab_settings // Assuming you have this string resource
    )

)
