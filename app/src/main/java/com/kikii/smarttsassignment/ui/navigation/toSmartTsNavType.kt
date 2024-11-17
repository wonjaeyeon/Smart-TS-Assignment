package com.kikii.smarttsassignment.ui.navigation

import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import com.kikii.smarttsassignment.ui.window.ReplyNavigationType

fun NavigationSuiteType.toSmartTsNavType() = when (this) {
    NavigationSuiteType.NavigationBar -> ReplyNavigationType.BOTTOM_NAVIGATION
    NavigationSuiteType.NavigationRail -> ReplyNavigationType.NAVIGATION_RAIL
    NavigationSuiteType.NavigationDrawer -> ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER
    else -> ReplyNavigationType.BOTTOM_NAVIGATION
}