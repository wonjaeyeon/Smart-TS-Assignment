package com.kikii.smarttsassignment.ui.feature.route

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kikii.smarttsassignment.R
import com.kikii.smarttsassignment.ui.components.card.SmartTsDriverInfoCard

@Composable
fun RouteScreen(
    modifier: Modifier = Modifier,
    viewModel: RouteViewModel = hiltViewModel()
) {
    val routeUiState by viewModel.routeUiState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))



        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.route_screen_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )



        when (routeUiState) {
            is RouteUiState.Loading -> Text("Loading route data...")
            is RouteUiState.Success -> {
                val route = (routeUiState as RouteUiState.Success).route
                SmartTsDriverInfoCard(route = route)

            }
            is RouteUiState.Error -> {
                val error = (routeUiState as RouteUiState.Error).exception
                Text("Failed to load route data: ${error.message}")
            }
        }
    }
}

@Preview
@Composable
fun RouteScreenPreview() {
    RouteScreen()
}