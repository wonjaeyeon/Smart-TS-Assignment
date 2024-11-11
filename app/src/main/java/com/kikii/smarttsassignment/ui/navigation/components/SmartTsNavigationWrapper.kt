package com.kikii.smarttsassignment.ui.navigation.components

import androidx.activity.compose.BackHandler
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldLayout
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavDestination
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.kikii.smarttsassignment.ui.navigation.TopLevelDestination
import com.kikii.smarttsassignment.ui.window.ReplyNavigationContentPosition
import kotlinx.coroutines.launch

private fun WindowSizeClass.isCompact() =
    windowWidthSizeClass == WindowWidthSizeClass.COMPACT ||
            windowHeightSizeClass == WindowHeightSizeClass.COMPACT


class ReplyNavSuiteScope(
    val navSuiteType: NavigationSuiteType
)

@Composable
fun SmartTsNavigationWrapper(
    currentDestination: NavDestination?,
    navigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    content: @Composable ReplyNavSuiteScope.() -> Unit
) {
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val windowSize = with(LocalDensity.current) {
        currentWindowSize().toSize().toDpSize()
    }

    val navLayoutType = when {
        adaptiveInfo.windowPosture.isTabletop -> NavigationSuiteType.NavigationBar
        adaptiveInfo.windowSizeClass.isCompact() -> NavigationSuiteType.NavigationBar
        adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED &&
                windowSize.width >= 1200.dp -> NavigationSuiteType.NavigationDrawer
        else -> NavigationSuiteType.NavigationRail
    }
    val navContentPosition = when (adaptiveInfo.windowSizeClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT -> ReplyNavigationContentPosition.TOP
        WindowHeightSizeClass.MEDIUM,
        WindowHeightSizeClass.EXPANDED -> ReplyNavigationContentPosition.CENTER
        else -> ReplyNavigationContentPosition.TOP
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    // Avoid opening the modal drawer when there is a permanent drawer or a bottom nav bar,
    // but always allow closing an open drawer.
    val gesturesEnabled =
        drawerState.isOpen || navLayoutType == NavigationSuiteType.NavigationRail

    BackHandler(enabled = drawerState.isOpen) {
        coroutineScope.launch {
            drawerState.close()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = gesturesEnabled,
        drawerContent = {
            ModalNavigationDrawerContent(
                currentDestination = currentDestination,
                navigationContentPosition = navContentPosition,
                navigateToTopLevelDestination = navigateToTopLevelDestination,
                onDrawerClicked = {
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
            )
        },
    ) {
        NavigationSuiteScaffoldLayout(
            layoutType = navLayoutType,
            navigationSuite = {
                when (navLayoutType) {
                    NavigationSuiteType.NavigationBar -> SmartTsBottomNavigationBar(
                        currentDestination = currentDestination,
                        navigateToTopLevelDestination = navigateToTopLevelDestination
                    )
                    NavigationSuiteType.NavigationRail -> SmartTsNavigationRail(
                        currentDestination = currentDestination,
                        navigationContentPosition = navContentPosition,
                        navigateToTopLevelDestination = navigateToTopLevelDestination,
                        onDrawerClicked = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }
                    )
                    NavigationSuiteType.NavigationDrawer -> PermanentNavigationDrawerContent(
                        currentDestination = currentDestination,
                        navigationContentPosition = navContentPosition,
                        navigateToTopLevelDestination = navigateToTopLevelDestination
                    )
                }
            }
        ) {
            ReplyNavSuiteScope(navLayoutType).content()
        }
    }
}
