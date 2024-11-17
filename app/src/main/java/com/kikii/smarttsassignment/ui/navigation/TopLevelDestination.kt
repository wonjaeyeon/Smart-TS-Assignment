package com.kikii.smarttsassignment.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class TopLevelDestination(
    val route: Route,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)