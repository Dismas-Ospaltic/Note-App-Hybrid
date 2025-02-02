package com.example.noteapphybrid.repository

import com.example.noteapphybrid.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class NotesRepository {
    // Sample data for notes
    private val sampleNotes = listOf(
        Note("Sample Note 1", "Details for note 1", "2025-02-01"),
        Note("Sample Note 2", "Details for note 2", "2025-02-02")
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