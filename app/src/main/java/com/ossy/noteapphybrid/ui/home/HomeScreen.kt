package com.ossy.noteapphybrid.ui.home


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.ossy.noteapphybrid.R
import com.ossy.noteapphybrid.model.NoteEntity
import com.ossy.noteapphybrid.utils.DynamicStatusBar
import com.ossy.noteapphybrid.viewmodel.NoteViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, noteViewModel: NoteViewModel = koinViewModel()) {
    val notes by noteViewModel.notes.collectAsState()
    val selectedNotes = remember { mutableStateOf<Set<String>>(emptySet()) }

    val backgroundColor = colorResource(id = R.color.white)
    DynamicStatusBar(backgroundColor)

    Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                title = {  androidx.compose.material.Text("Notes") },
                backgroundColor = colorResource(id = R.color.white),
                modifier = Modifier.padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),
                actions = {
                    if (selectedNotes.value.isNotEmpty()) {
                        IconButton(onClick = {
                            // Handle delete action
                            selectedNotes.value = emptySet()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_delete),
                                contentDescription = "Delete"
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(onClick = {
                            // Handle pin action
//                            selectedNotes.clear()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_pin),
                                contentDescription = "Pin"
                            )
                        }
                    }
                }
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_note") },
                containerColor = colorResource(id = R.color.teal_700),
                modifier = Modifier
                    .padding(bottom = 70.dp, end = 16.dp)
                    .zIndex(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_white),
                    contentDescription = "Add Note"
                )
            }
        }
    ) { paddingValues ->
        if (notes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("No notes available")
            }
        } else {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(notes) { note ->
                    val isSelected = selectedNotes.value.contains(note.noteId)

                    NoteItem(
                        note = note,
                        isSelected = isSelected,
                        onLongPress = {
                            selectedNotes.value = if (isSelected) {
                                selectedNotes.value - note.noteId
                            } else {
                                selectedNotes.value + note.noteId
                            }
                        },
                        onClick = {
                            if (selectedNotes.value.isNotEmpty()) {
                                selectedNotes.value = if (isSelected) {
                                    selectedNotes.value - note.noteId
                                } else {
                                    selectedNotes.value + note.noteId
                                }
                            } else {
                                // Handle regular click (e.g., navigate to note details)
                                navController.navigate("edit_note/${note.noteId}")
                            }
                        }
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteItem(
    note: NoteEntity,
    isSelected: Boolean,
    onLongPress: () -> Unit,
    onClick: () -> Unit
) {
    val hapticFeedback = LocalHapticFeedback.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    onLongPress()
                }),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                colorResource(id = R.color.colorful)
            } else {
                colorResource(id = R.color.transparent_dark)
            }
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (isSelected) {
                Checkbox(
                    checked = true,
                    onCheckedChange = { onLongPress() }
                )
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(note.title, style = MaterialTheme.typography.titleLarge)
                Text(note.content, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = convertLongToDate(note.timestamp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}



fun convertLongToDate(time: Long): String {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return format.format(Date(time))
}

