package com.kikii.smarttsassignment.ui.navigation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import com.kikii.smarttsassignment.ui.navigation.TOP_LEVEL_DESTINATIONS
import com.kikii.smarttsassignment.ui.navigation.TopLevelDestination
import com.kikii.smarttsassignment.ui.navigation.components.policy.LayoutType
import com.kikii.smarttsassignment.ui.navigation.components.policy.hasRoute
import com.kikii.smarttsassignment.ui.navigation.components.policy.navigationMeasurePolicy
import com.kikii.smarttsassignment.ui.window.ReplyNavigationContentPosition
import com.kikii.smarttsassignment.R
import com.kikii.smarttsassignment.ui.feature.auth.AuthUiState
import com.kikii.smarttsassignment.ui.feature.auth.LoginViewModel

// just for error ( internal on jetbrains)
@SuppressLint("RestrictedApi")
@Composable
fun ModalNavigationDrawerContent(
    currentDestination: NavDestination?,
    navigationContentPosition: ReplyNavigationContentPosition,
    navigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    onDrawerClicked: () -> Unit = {},
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    val user = loginViewModel.authUiState.collectAsStateWithLifecycle()
    val loginId = if (user.value is AuthUiState.Success) {
        (user.value as AuthUiState.Success).authModel.loginId
    } else {
        ""
    }
    val userRoleName = if (user.value is AuthUiState.Success) {
        (user.value as AuthUiState.Success).authModel.userRoleName
    } else {
        ""
    }

    ModalDrawerSheet {
        // TODO remove custom nav drawer content positioning when NavDrawer component supports it. ticket : b/232495216
        Layout(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .padding(16.dp),
            content = {
                Column(
                    modifier = Modifier.layoutId(LayoutType.HEADER),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.app_name).uppercase(),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        IconButton(onClick = onDrawerClicked) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = stringResource(id = R.string.close_drawer)
                            )
                        }
                    }

                    ExtendedFloatingActionButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 40.dp),
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                    ) {
                        Icon(
                            imageVector = Icons.Default.PersonOutline,
                            contentDescription = stringResource(id = R.string.compose),
                            modifier = Modifier.size(36.dp)
                        )
                        Column(
                            modifier = Modifier
                                .width(100.dp) // Set a fixed width
                                .height(IntrinsicSize.Min) // Set height based on the minimum height of the content
                                .padding(horizontal = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally // Center text within the Column
                        )
                        {
                            Text(
                                text = loginId,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = userRoleName,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                        }


                    }
                }

                Column(
                    modifier = Modifier
                        .layoutId(LayoutType.CONTENT)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TOP_LEVEL_DESTINATIONS.forEach { replyDestination ->
                        NavigationDrawerItem(
                            selected = currentDestination.hasRoute(replyDestination),
                            label = {
                                Text(
                                    text = stringResource(id = replyDestination.iconTextId),
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = replyDestination.selectedIcon,
                                    contentDescription = stringResource(
                                        id = replyDestination.iconTextId
                                    )
                                )
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = Color.Transparent
                            ),
                            onClick = { navigateToTopLevelDestination(replyDestination) }
                        )
                    }
                }
            },
            measurePolicy = navigationMeasurePolicy(navigationContentPosition)
        )
    }
}