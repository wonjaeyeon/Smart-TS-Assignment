package com.kikii.smarttsassignment.ui.feature.dispatch

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kikii.smarttsassignment.R

@Composable
fun DispatchScreen(
    modifier: Modifier = Modifier,

){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.dispatch_screen_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.dispatch_screen_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.outline
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.dispatch_screen_no_dispatches),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.outline
        )
    }
}