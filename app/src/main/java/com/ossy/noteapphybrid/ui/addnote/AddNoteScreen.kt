package com.ossy.noteapphybrid.ui.addnote

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ossy.noteapphybrid.R
import com.ossy.noteapphybrid.model.NoteEntity
import com.ossy.noteapphybrid.viewmodel.NoteViewModel
import org.koin.androidx.compose.koinViewModel
import java.util.*

@Composable
fun AddNoteScreen(navController: NavController, noteViewModel: NoteViewModel = koinViewModel()) {
    // Mutable states to store the input values.
//    var title by remember { mutableStateOf("") }
//    var noteContent by remember { mutableStateOf("") }

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

//    // Get the current date
//    val currentDate = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()) }
// Get the current date in Long format
    val currentDate = remember { System.currentTimeMillis() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Note") },
                modifier = Modifier.padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),
                navigationIcon = {
                    IconButton(onClick = {
                        // Use NavController to navigate back
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                backgroundColor = colorResource(id = R.color.white)

            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (title.isNotBlank() && content.isNotBlank()) {
                        // Save the note via ViewModel and navigate back
                        noteViewModel.insert(NoteEntity(title = title, noteId= generateUniqueId(), content = content))
                        navController.popBackStack()  // Navigate back after saving
                    }

                },
                backgroundColor = colorResource(id = R.color.teal_700),
                modifier = Modifier
                    .padding(bottom = 70.dp, end = 16.dp)
                    .zIndex(1f)

            ) {
                Icon(Icons.Default.Check, contentDescription = "Save Note")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Title TextField
            BasicTextField(
                value = title,
                onValueChange = { title = it },
                textStyle = TextStyle(fontSize = 24.sp, color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                decorationBox = { innerTextField ->
                    if (title.isEmpty()) {
                        Text("Title", color = Color.Gray, fontSize = 24.sp)
                    }
                    innerTextField()
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
//                maxLines = Int.MAX_VALUE // Allows unlimited lines
                maxLines = 100,
                singleLine = false // Allow multiline input
            )

//            // Note Content TextField
//            BasicTextField(
//                value = content,
//                onValueChange = { content = it },
//                textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(vertical = 8.dp),
//                decorationBox = { innerTextField ->
//                    if (content.isEmpty()) {
//                        Text("Note something down", color = Color.Gray, fontSize = 18.sp)
//                    }
//                    innerTextField()
//                },
//                keyboardOptions = KeyboardOptions.Default.copy(
//                    imeAction = ImeAction.Done,
//                    keyboardType = KeyboardType.Text
//                ),
//                keyboardActions = KeyboardActions(onDone = {
//                    if (title.isNotBlank() && content.isNotBlank()) {
//                        noteViewModel.insert(NoteEntity(title = title, noteId= generateUniqueId(), content = content))
//                        navController.popBackStack()  // Navigate back after saving
//                    }
//                }),
////                maxLines = Int.MAX_VALUE // Allows unlimited lines
//                maxLines = 100,
//                singleLine = false // Allow multiline input
//
//            )


            // Note Content TextField
            BasicTextField(
                value = content,
                onValueChange = { content = it },
                textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp),
                decorationBox = { innerTextField ->
                    if (content.isEmpty()) {
                        Text("Note something down", color = Color.Gray, fontSize = 18.sp)
                    }
                    innerTextField()
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.None, // Set to None to prevent the "Done" action
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Handle the "Done" action if needed
                    }
                ),
                maxLines = Int.MAX_VALUE, // Allows unlimited lines
                singleLine = false // Allow multiline input
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddNoteScreenPreview() {
    AddNoteScreen(navController = rememberNavController())
}


fun generateUniqueId(): String {
    return UUID.randomUUID().toString()
}