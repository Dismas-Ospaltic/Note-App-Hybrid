package com.ossy.noteapphybrid.ui.editnote

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ossy.noteapphybrid.R
import com.ossy.noteapphybrid.model.NoteEntity
import com.ossy.noteapphybrid.viewmodel.NoteViewModel
import java.util.*


@Composable
fun EditNoteScreen(
    navController: NavController,
    noteId: String,
    noteViewModel: NoteViewModel
) {
    // Trigger data loading
    LaunchedEffect(noteId) {
        noteViewModel.getNoteDetails(noteId)
    }

    // Observe noteState
    val note by noteViewModel.noteState.collectAsState()

    // State for title and content
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    // Update title and content when note is loaded
    LaunchedEffect(note) {
        note?.let {
            title = it.title
            content = it.content
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Note") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
                        // Update the note via ViewModel and navigate back
                        noteViewModel.update(
                            NoteEntity(
                                title = title,
                                content = content,
                                noteId = noteId,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                        navController.popBackStack()  // Navigate back after saving
                    }
                }
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
                maxLines = 100,
                singleLine = false
            )

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
                    imeAction = ImeAction.None,
                    keyboardType = KeyboardType.Text
                ),
                maxLines = Int.MAX_VALUE,
                singleLine = false
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AddNoteScreenPreview() {
//    EditNoteScreen(
//        navController = rememberNavController(),
//        noteId = TODO(),
//        noteViewModel = Note
//    )
//}


fun generateUniqueId(): String {
    return UUID.randomUUID().toString()
}