package com.example.noteapphybrid.repository

import com.example.noteapphybrid.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.text.SimpleDateFormat
import java.util.Locale


class NotesRepository {



    // Function to convert date string to Long timestamp
    fun parseDateToLong(dateString: String): Long {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.parse(dateString)?.time ?: System.currentTimeMillis()
    }

    // Sample notes with Long dates
    private val sampleNotes = listOf(
        Note(1707078645123456789, "Sample Note 1", "Details for note 1", parseDateToLong("2025-02-01")),
        Note(1707078645123454489, "Sample Note 2", "Details for note 2", parseDateToLong("2025-02-02")),
        Note(1707070945123456789, "Sample Note 3", "Details for note 3", parseDateToLong("2025-02-03")),
        Note(1707072945123456789, "Sample Note 4", "Details for note 4", parseDateToLong("2025-02-04")),
        Note(1707045645123456789, "Sample Note 5", "Details for note 5", parseDateToLong("2025-02-05")),
        Note(1707578645123456789, "Sample Note 6", "Details for note 6", parseDateToLong("2025-02-06")),
        Note(1037078645123456789, "Sample Note 7", "Details for note 7", parseDateToLong("2025-02-07")),
        Note(1707074545123456789, "Sample Note 8", "Details for note 8", parseDateToLong("2025-02-08")),
        Note(1707078645190456789,"Sample Note 9", "Details for note 9", parseDateToLong("2025-02-09"))
    )


//    // Sample data for notes
//    private val sampleNotes = listOf(
//        Note("Sample Note 1", "Details for note 1", "2025-02-01"),
//        Note("Sample Note 2", "Details for note 2", "2025-02-02"),
//        Note("Sample Note 3", "Details for note 3", "2025-02-03"),
//        Note("Sample Note 4", "Details for note 4", "2025-02-04"),
//        Note("Sample Note 5", "Details for note 5", "2025-02-05"),
//        Note("Sample Note 6", "Details for note 6", "2025-02-06"),
//        Note("Sample Note 7", "Details for note 7", "2025-02-07"),
//        Note("Sample Note 8", "Details for note 8", "2025-02-08"),
//        Note("Sample Note 9", "Details for note 9", "2025-02-09"),
//
//    )

    // Function to get the notes
    fun getNotes(): Flow<List<Note>> {
        return flowOf(sampleNotes) // In real app, replace with data source like DB
    }

    // Function to add a note
    suspend fun addNote(note: Note) {
        // Add note to your data source (e.g., database or API)
    }
}