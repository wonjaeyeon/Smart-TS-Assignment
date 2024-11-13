package com.kikii.smarttsassignment.ui.components.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun SmartTsItemList(listItems: List<ListItem>, navController: NavHostController) {
    LazyColumn {
        items(listItems) { item ->
            ListItem(item, navController)
        }
    }
}

