package com.example.noteapphybrid.viewmodel

//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.noteapphybrid.model.Note
//import com.example.noteapphybrid.repository.NotesRepository
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.launch
//
//
//class NotesViewModel(private val notesRepository: NotesRepository) : ViewModel() {
//        // Flow of notes to be observed by the UI
//        val notes: Flow<List<Note>> = notesRepository.getNotes()
//
//        fun addNote(note: Note) {
//            viewModelScope.launch {
//                notesRepository.addNote(note)
//            }
//        }
//}