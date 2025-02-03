package com.example.noteapphybrid.repository

import com.example.noteapphybrid.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class NotesRepository {
    // Sample data for notes
    private val sampleNotes = listOf(
        Note("Sample Note 1", "Details for note 1", "2025-02-01"),
        Note("Sample Note 2", "Details for note 2", "2025-02-02"),
        Note("Sample Note 3", "Details for note 3", "2025-02-03"),
        Note("Sample Note 4", "Details for note 4", "2025-02-04"),
        Note("Sample Note 5", "Details for note 5", "2025-02-05"),
        Note("Sample Note 6", "Details for note 6", "2025-02-06"),
        Note("Sample Note 7", "Details for note 7", "2025-02-07"),
        Note("Sample Note 8", "Details for note 8", "2025-02-08"),
        Note("Sample Note 9", "Details for note 9", "2025-02-09"),

    )

    // Function to get the notes
    fun getNotes(): Flow<List<Note>> {
        return flowOf(sampleNotes) // In real app, replace with data source like DB
    }

    // Function to add a note
    suspend fun addNote(note: Note) {
        // Add note to your data source (e.g., database or API)
    }
}