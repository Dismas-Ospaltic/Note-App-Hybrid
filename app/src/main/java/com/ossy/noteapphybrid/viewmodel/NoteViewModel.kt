package com.ossy.noteapphybrid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ossy.noteapphybrid.model.NoteEntity
import com.ossy.noteapphybrid.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    private val _notes = MutableStateFlow<List<NoteEntity>>(emptyList())
    val notes: StateFlow<List<NoteEntity>> = _notes

    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            repository.allNotes.collect { noteList ->
                _notes.value = noteList
            }
        }
    }

    fun insert(note: NoteEntity) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    fun update(note: NoteEntity) {
        viewModelScope.launch {
            repository.update(note)
        }
    }

    fun delete(note: NoteEntity) {
        viewModelScope.launch {
            repository.delete(note)
        }
    }


    private val _noteState = MutableStateFlow<NoteEntity?>(null)
    val noteState: StateFlow<NoteEntity?> = _noteState

    fun getNoteDetails(noteId: String) {
        viewModelScope.launch {
            val note = repository.getNoteById(noteId)
            _noteState.value = note
        }
    }

//    fun getNoteDetails(noteId: String): LiveData<NoteEntity?> {
//        val result = MutableLiveData<NoteEntity?>()
//        viewModelScope.launch {
//            result.value = repository.getNoteById(noteId)
//        }
//        return result
//    }

}
