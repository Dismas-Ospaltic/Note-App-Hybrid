package com.example.noteapphybrid.ui.mainscreen

import AddNoteScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noteapphybrid.ui.account.AccountScreen
import com.example.noteapphybrid.ui.components.BottomNavigationBar
import com.example.noteapphybrid.ui.home.HomeScreen
import com.example.noteapphybrid.ui.todo.ToDoScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.noteapphybrid.data.local.AppDatabase
import com.example.noteapphybrid.repository.NoteRepository
import com.example.noteapphybrid.viewmodel.NoteViewModel

@Composable
fun MainScreen(noteViewModel: NoteViewModel) {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val items = listOf("home", "to_do", "account")
    val selectedIndex = items.indexOf(currentRoute).takeIf { it != -1 } ?: 0

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") {
                HomeScreen(navController = navController)
            }
            composable("to_do") {
                ToDoScreen(navController = navController)
            }
            composable("account") {
                AccountScreen(navController = navController)
            }
            composable("add_note") {
                AddNoteScreen(navController = navController)
            }
        }
    }
}
