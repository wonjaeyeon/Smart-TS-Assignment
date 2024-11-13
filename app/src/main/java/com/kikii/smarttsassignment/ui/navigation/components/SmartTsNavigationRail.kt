package com.kikii.smarttsassignment.ui.navigation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.kikii.smarttsassignment.R
import com.kikii.smarttsassignment.ui.navigation.TOP_LEVEL_DESTINATIONS
import com.kikii.smarttsassignment.ui.navigation.TopLevelDestination
import com.kikii.smarttsassignment.ui.navigation.components.policy.LayoutType
import com.kikii.smarttsassignment.ui.window.ReplyNavigationContentPosition

import com.kikii.smarttsassignment.ui.navigation.components.policy.hasRoute

@Composable
fun SmartTsNavigationRail(
    currentDestination: NavDestination?,
    navigationContentPosition: ReplyNavigationContentPosition,
    navigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    onDrawerClicked: () -> Unit = {},
) {
    NavigationRail(
        modifier = Modifier.fillMaxHeight(),
        containerColor = MaterialTheme.colorScheme.inverseOnSurface
    ) {
        Column(
            modifier = Modifier.layoutId(LayoutType.HEADER),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            NavigationRailItem(
                selected = false,
                onClick = onDrawerClicked,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = stringResource(id = R.string.navigation_drawer)
                    )
                }
            )
            FloatingActionButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(top = 8.dp, bottom = 32.dp),
                //containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.PersonOutline,
                    contentDescription = stringResource(id = R.string.compose),
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(Modifier.height(8.dp)) // NavigationRailHeaderPadding
            Spacer(Modifier.height(4.dp)) // NavigationRailVerticalPadding
        }

        Column(
            modifier = Modifier.layoutId(LayoutType.CONTENT),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            TOP_LEVEL_DESTINATIONS.forEach { replyDestination ->
                NavigationRailItem(
                    selected = currentDestination.hasRoute(replyDestination),
                    onClick = { navigateToTopLevelDestination(replyDestination) },
                    icon = {
                        Icon(
                            imageVector = replyDestination.selectedIcon,
                            contentDescription = stringResource(
                                id = replyDestination.iconTextId
                            )
                        )
                    }
                )
            }
        }
    }
}
