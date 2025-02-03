//package com.example.noteapphybrid.ui.todo
//
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.example.noteapphybrid.R
//import com.example.noteapphybrid.ui.components.BottomNavigationBar
//
//@Composable
//fun ToDoScreen( navController: NavController) { //navController: NavController
//
//    var selectedIndex by remember { mutableStateOf(1) } // Set default index to "To-Do"
//
//    Scaffold(
//        topBar = {
//            androidx.compose.material.TopAppBar(
//                title = { androidx.compose.material.Text("To-do") },
//                navigationIcon = {
//                    androidx.compose.material.IconButton(onClick = {
//
//                    }) {
//                        androidx.compose.material.Icon(
//                            painter = painterResource(id = R.drawable.note_gray), // Use custom icon
//                            contentDescription = "note icon"
//                        )
//                    }
//                },
//                backgroundColor = colorResource(id = R.color.white)
//
//            )
//        },
//        bottomBar = { BottomNavigationBar(navController, selectedIndex) { selectedIndex = it } }
//    ) {
//            paddingValues ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues) // Ensures content is positioned correctly inside Scaffold
//                .padding(16.dp) // Additional padding for spacing
//        ) {
//            Column(modifier = Modifier.fillMaxSize()) {
//                Text("To-Do List")
//                Spacer(modifier = Modifier.height(8.dp))
//                // Add more content here
//            }
//        }
//    }
//}
//
//
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

