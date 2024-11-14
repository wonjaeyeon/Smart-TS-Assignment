package com.kikii.smarttsassignment.ui.feature.dispatch

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kikii.smarttsassignment.R
import com.kikii.smarttsassignment.data.model.DispatchModel
import com.kikii.smarttsassignment.ui.feature.dispatch.components.CustomDatePickerDialog
import com.kikii.smarttsassignment.ui.feature.dispatch.components.DispatchItem

//
//@Composable
//fun DispatchScreen(
//    modifier: Modifier = Modifier,
//
//){
//    Column(
//        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
//    )
//    {
//        Spacer(modifier = Modifier.height(40.dp))
//
//        Text(
//            modifier = Modifier.padding(8.dp),
//            text = stringResource(id = R.string.dispatch_screen_title),
//            style = MaterialTheme.typography.titleLarge,
//            textAlign = TextAlign.Center,
//            color = MaterialTheme.colorScheme.primary
//        )
//
//        Text(
//            modifier = Modifier.padding(8.dp),
//            text = stringResource(id = R.string.dispatch_screen_subtitle),
//            style = MaterialTheme.typography.bodyMedium,
//            textAlign = TextAlign.Center,
//            color = MaterialTheme.colorScheme.outline
//        )
//
//        Text(
//            modifier = Modifier.padding(8.dp),
//            text = stringResource(id = R.string.dispatch_screen_no_dispatches),
//            style = MaterialTheme.typography.bodyMedium,
//            textAlign = TextAlign.Center,
//            color = MaterialTheme.colorScheme.outline
//        )
//    }
//}

//@Composable
//fun DispatchScreen(
//    modifier: Modifier = Modifier,
//    viewModel: DispatchViewModel = hiltViewModel()
//) {
//    val dispatchUiState by viewModel.dispatchUiState.collectAsState()
//
//    Column(
//        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Spacer(modifier = Modifier.height(40.dp))
//        Text(
//            modifier = Modifier.padding(8.dp),
//            text = stringResource(id = R.string.dispatch_screen_title),
//            style = MaterialTheme.typography.titleLarge,
//            textAlign = TextAlign.Center,
//            color = MaterialTheme.colorScheme.primary
//        )
//        Text(
//            modifier = Modifier.padding(horizontal = 8.dp),
//            text = stringResource(id = R.string.dispatch_screen_subtitle),
//            style = MaterialTheme.typography.bodySmall,
//            textAlign = TextAlign.Center,
//            color = MaterialTheme.colorScheme.outline
//        )
//
//        when (dispatchUiState) {
//            is DispatchUiState.Loading -> Text("Loading dispatch data...")
//            is DispatchUiState.Success -> {
//                val dispatchList = (dispatchUiState as DispatchUiState.Success).data
//                if (dispatchList.isNullOrEmpty()) {
//                    Text("There are no dispatches")
//                } else {
//                    LazyColumn {
//                        items(dispatchList) { dispatch ->
//                            DispatchItem(dispatch = dispatch)
//                        }
//                    }
//                }
//            }
//            is DispatchUiState.Error -> {
//                val error = (dispatchUiState as DispatchUiState.Error).exception
//                Text("Failed to load dispatch data: ${error.message}")
//            }
//        }
//    }
//}
//
//@Composable
//fun DispatchItem(dispatch: DispatchModel?) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//    ) {
//        Text("Driver Name: ${dispatch?.driverName}")
//        Text("Route Name: ${dispatch?.routeName}")
//        Text("Bus Number: ${dispatch?.busNumber}")
//        Text("Start Time: ${dispatch?.startTime}")
//        Spacer(modifier = Modifier.height(8.dp))
//    }
//}
//
//@Preview
//@Composable
//fun DispatchScreenPreview() {
//    DispatchScreen()
//}

@Composable
fun DispatchScreen(
    modifier: Modifier = Modifier,
    viewModel: DispatchViewModel = hiltViewModel()
) {
    val dispatchUiState by viewModel.dispatchUiState.collectAsState()
    val availableDates = viewModel.availableDates.collectAsState()
    val selectedDate by viewModel.selectedDate.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.dispatch_screen_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )

//        Button(onClick = { showDialog = true }) {
//            Text("Select Date")
//        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Display selected date or default message
            Text(
                text = selectedDate ?: "Please select the Date with Calendar",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f) // Use weight to take up available space
            )

            // Icon button for date selection aligned to the right
            IconButton(onClick = { showDialog = true }) {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "Select Date",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        if (showDialog) {
            CustomDatePickerDialog(
                availableDates = availableDates.value,
                onDismissRequest = { showDialog = false },
                onDateSelected = { date ->
                    viewModel.selectDate(date)
                    showDialog = false
                }
            )
        }

        // Display Dispatch Data
        when (dispatchUiState) {
            is DispatchUiState.Loading -> Text("Loading dispatch data...")
            is DispatchUiState.Success -> {
                val dispatchList = (dispatchUiState as DispatchUiState.Success).data
                if (dispatchList.isNullOrEmpty()) {
                    Text("There are no dispatches")
                } else {
                    LazyColumn {
                        items(dispatchList) { dispatch ->
                            DispatchItem(dispatch = dispatch)
                        }
                    }
                }
            }
            is DispatchUiState.Error -> {
                val error = (dispatchUiState as DispatchUiState.Error).exception
                Text("Failed to load dispatch data: ${error.message}")
            }
        }
    }
}

@Preview
@Composable
fun DispatchScreenPreview() {
    DispatchScreen()
}