package com.kikii.smarttsassignment.ui.navigation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import com.kikii.smarttsassignment.ui.navigation.TOP_LEVEL_DESTINATIONS
import com.kikii.smarttsassignment.ui.navigation.TopLevelDestination
import com.kikii.smarttsassignment.ui.navigation.components.policy.hasRoute

@Composable
fun SmartTsBottomNavigationBar(
    currentDestination: NavDestination?,
    navigateToTopLevelDestination: (TopLevelDestination) -> Unit
) {
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        TOP_LEVEL_DESTINATIONS.forEach { replyDestination ->
            NavigationBarItem(
                selected = currentDestination.hasRoute(replyDestination),
                onClick = { navigateToTopLevelDestination(replyDestination) },
                icon = {
                    Icon(
                        imageVector = replyDestination.selectedIcon,
                        contentDescription = stringResource(id = replyDestination.iconTextId)
                    )
                }
            )
        }
    }
}