
package com.example.noteapphybrid.ui.mainscreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noteapphybrid.ui.account.AccountScreen
import com.example.noteapphybrid.ui.components.BottomNavigationBar
import com.example.noteapphybrid.ui.home.HomeScreen
import com.example.noteapphybrid.ui.todo.ToDoScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.noteapphybrid.data.datastore.UserPreferencesManager
import com.example.noteapphybrid.ui.addnote.AddNoteScreen
import com.example.noteapphybrid.ui.editnote.EditNoteScreen
import com.example.noteapphybrid.viewmodel.NoteViewModel

@Composable
fun MainScreen(noteViewModel: NoteViewModel, userPreferences: UserPreferencesManager) {
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
                AccountScreen(navController = navController, userPreferences = userPreferences) // Pass userPreferences
            }
            composable("add_note") {
                AddNoteScreen(navController = navController)
            }
            composable(
                route = "edit_note/{noteId}",
                arguments = listOf(navArgument("noteId") { type = NavType.StringType })
            ) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getString("noteId")
                noteId?.let {
                    EditNoteScreen(navController = navController, noteId = it, noteViewModel = noteViewModel)
                }
            }
        }
    }
}

