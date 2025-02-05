//package com.example.noteapphybrid.ui.home
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.BottomNavigation
//import androidx.compose.material.BottomNavigationItem
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import androidx.compose.ui.res.painterResource
//import com.example.noteapphybrid.R
//import com.example.noteapphybrid.model.Note
//import com.example.noteapphybrid.repository.NotesRepository
//import com.example.noteapphybrid.ui.components.BottomNavigationBar
//import com.example.noteapphybrid.viewmodel.NotesViewModel
//import org.koin.androidx.compose.koinViewModel
//
//@Composable
//fun HomeScreen(viewModel: NotesViewModel = koinViewModel(), navController: NavController) {
//
//    var selectedIndex by remember { mutableStateOf(0) }
//
//    val notes by viewModel.notes.collectAsState(initial = emptyList())
//
//    Scaffold(
//        topBar = {
//            androidx.compose.material.TopAppBar(
//                title = { androidx.compose.material.Text("Notes") },
//                navigationIcon = {
//                    androidx.compose.material.IconButton(onClick = {
//
//                    }) {
//                        androidx.compose.material.Icon(
//                           painter = painterResource(id = R.drawable.note_gray), // Use custom icon
//                    contentDescription = "note icon"
//                        )
//                    }
//                },
//                backgroundColor = colorResource(id = R.color.white)
//
//            )
//        },
////        bottomBar = { BottomNavigationBar(navController) }, // ✅ Pass NavController
//        bottomBar = { BottomNavigationBar(navController, selectedIndex) { selectedIndex = it } },
//        floatingActionButton = {
//            FloatingActionButton(onClick = { /* Handle Add Note */
//                navController.navigate("add_note")
//            },
//                containerColor = colorResource(id = R.color.green), // Set background color
//                ) {
//                // Use icon from drawable folder using painterResource
//                Image(
//                    painter = painterResource(id = R.drawable.add_dark), // Replace with your icon name
//                    contentDescription = "Add Note"
//                )
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
////        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Material 3 way
//        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.transparent_dark)), // Fetch color from resources
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(note.title, style = MaterialTheme.typography.titleLarge)
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(note.details, style = MaterialTheme.typography.bodyMedium)
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(note.date, style = MaterialTheme.typography.bodySmall)
//        }
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen(viewModel = NotesViewModel(NotesRepository()), navController = rememberNavController())
//}
//

package com.example.noteapphybrid.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteapphybrid.R
import com.example.noteapphybrid.model.NoteEntity
import com.example.noteapphybrid.viewmodel.NoteViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(navController: NavController, noteViewModel: NoteViewModel = koinViewModel()) {

    val notes by noteViewModel.notes.collectAsState()

    Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                title = { androidx.compose.material.Text("Notes") },
                backgroundColor = colorResource(id = R.color.white)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add_note") },
                containerColor = colorResource(id = R.color.green)) {
                Icon(painter = painterResource(id = R.drawable.add_dark), contentDescription = "Add Note")
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
//            items(notes) { note -> NoteItem(note) }

            items(notes) { note ->
                NoteItem(note, onDelete = { noteViewModel.delete(note) })
            }
        }
    }


}

@Composable
fun NoteItem(note: NoteEntity, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.transparent_dark))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(note.title, style = MaterialTheme.typography.titleLarge)
            Text(note.content, style = MaterialTheme.typography.bodyMedium)
//            Text(note.date, style = MaterialTheme.typography.bodySmall)
            Text(
                text = convertLongToDate(note.timestamp),
                style = MaterialTheme.typography.bodySmall
            )
        }

    }
}

// Function to convert Long timestamp to a readable date string
fun convertLongToDate(time: Long): String {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return format.format(Date(time))
}