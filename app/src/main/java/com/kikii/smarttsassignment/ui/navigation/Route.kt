package com.kikii.smarttsassignment.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Router : Route
    @Serializable
    data object Dispatcher : Route
    @Serializable
    data object Settings : Route
}
