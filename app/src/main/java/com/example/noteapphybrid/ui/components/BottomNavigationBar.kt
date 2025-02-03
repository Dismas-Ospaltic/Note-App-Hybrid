package com.example.noteapphybrid.ui.components
import androidx.compose.foundation.Image
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.compose.ui.res.painterResource
import com.example.noteapphybrid.R
@Composable
fun BottomNavigationBar(navController: NavController, selectedIndex: Int, onTabSelected: (Int) -> Unit) {
    BottomNavigation(backgroundColor = Color.White) {
        BottomNavigationItem(
            selected = selectedIndex == 0,
            onClick = {
                onTabSelected(0)
                navController.navigate("home") // Navigate to Notes screen
            },
            icon = {
                Image(
                    painter = painterResource(
                        id = if (selectedIndex == 0) R.drawable.notes_green else R.drawable.note_gray
                    ),
                    contentDescription = "Notes"
                )
            },
            label = {
                Text(
                    "Notes",
                    color = if (selectedIndex == 0) Color.Green else Color.Gray
                )
            }
        )

        BottomNavigationItem(
            selected = selectedIndex == 1,
            onClick = {
                onTabSelected(1)
                navController.navigate("to_do") // Navigate to To-Do screen
            },
            icon = {
                Image(
                    painter = painterResource(
                        id = if (selectedIndex == 1) R.drawable.todo_green else R.drawable.todo_gray
                    ),
                    contentDescription = "To-Do"
                )
            },
            label = {
                Text(
                    "To-Do",
                    color = if (selectedIndex == 1) Color.Green else Color.Gray
                )
            }
        )

        BottomNavigationItem(
            selected = selectedIndex == 2,
            onClick = {
                onTabSelected(2)
                navController.navigate("account") // Navigate to Account screen
            },
            icon = {
                Image(
                    painter = painterResource(
                        id = if (selectedIndex == 2) R.drawable.user_green else R.drawable.user_gray
                    ),
                    contentDescription = "Account"
                )
            },
            label = {
                Text(
                    "Account",
                    color = if (selectedIndex == 2) Color.Green else Color.Gray
                )
            }
        )
    }
}
