package com.example.noteapphybrid.ui.todo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.noteapphybrid.R

@Composable
fun ToDoScreen(navController: NavController) {
    Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                title = { androidx.compose.material.Text("To-Do") },
                backgroundColor = colorResource(id = R.color.white)
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Text("To-Do List", modifier = Modifier.padding(16.dp))
        }
    }
}

