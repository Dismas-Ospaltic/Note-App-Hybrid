package com.example.noteapphybrid.ui.home


//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material3.Card
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.noteapphybrid.model.Note
//import com.example.noteapphybrid.ui.login.LoginScreen
//import com.example.noteapphybrid.viewmodel.NotesViewModel
//import org.koin.androidx.compose.koinViewModel
//
//@Composable
//fun HomeScreen(viewModel: NotesViewModel = koinViewModel(), navController: NavController) {
//    val notes by viewModel.notes.collectAsState(initial = emptyList())
//
//    Scaffold(
//        bottomBar = { BottomNavigationBar() },
//        floatingActionButton = {
//            FloatingActionButton(onClick = { /* Handle Add Note */ }) {
//                Icon(Icons.Default.Add, contentDescription = "Add Note")
//            }
//        }
//    ) {
//        LazyColumn(modifier = Modifier.padding(it)) {
//            items(notes) { note ->
//                NoteItem(note)
//            }
//        }
//    }
//}
//
//@Composable
//fun NoteItem(note: Note) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        elevation = 4.dp
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(note.title, style = MaterialTheme.typography.h6)
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(note.details, style = MaterialTheme.typography.body2)
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(note.date, style = MaterialTheme.typography.caption)
//        }
//    }
//}
//
//@Composable
//fun BottomNavigationBar() {
//    BottomNavigation(backgroundColor = Color.White) {
//        BottomNavigationItem(
//            selected = true,
//            onClick = { /* Handle Notes */ },
//            icon = { Icon(Icons.Default.Notes, contentDescription = "Notes") },
//            label = { Text("Notes") }
//        )
//        BottomNavigationItem(
//            selected = false,
//            onClick = { /* Handle To-Do */ },
//            icon = { Icon(Icons.Default.Check, contentDescription = "To-Do") },
//            label = { Text("To-Do") }
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen(navController = rememberNavController())
//}




import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.res.painterResource
import com.example.noteapphybrid.R
import com.example.noteapphybrid.model.Note
import com.example.noteapphybrid.repository.NotesRepository
import com.example.noteapphybrid.viewmodel.NotesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: NotesViewModel = koinViewModel(), navController: NavController) {
    val notes by viewModel.notes.collectAsState(initial = emptyList())

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }, // ✅ Pass NavController
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Handle Add Note */
                navController.navigate("add_note")
            }) {
                // Use icon from drawable folder using painterResource
                Image(
                    painter = painterResource(id = R.drawable.add_dark), // Replace with your icon name
                    contentDescription = "Add Note"
                )
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(notes) { note ->
                NoteItem(note)
            }
        }
    }
}

@Composable
fun NoteItem(note: Note) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Material 3 way
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(note.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(note.details, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(note.date, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = { /* Handle Notes */ },
            icon = {
                // Use icon from drawable folder
                Image(
                    painter = painterResource(id = R.drawable.note_gray), // Replace with your icon name
                    contentDescription = "Notes"
                )
            },
            label = { Text("Notes") }
        )
        BottomNavigationItem(
            selected = false,
            onClick = { /* Handle To-Do */

            },
            icon = {
                // Use icon from drawable folder
                Image(
                    painter = painterResource(id = R.drawable.todo_gray), // Replace with your icon name
                    contentDescription = "To-Do"
                )
            },
            label = { Text("To-Do") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(viewModel = NotesViewModel(NotesRepository()), navController = rememberNavController())
}

