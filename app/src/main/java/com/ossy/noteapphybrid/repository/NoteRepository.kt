package com.ossy.noteapphybrid.repository


import com.ossy.noteapphybrid.data.local.NoteDao
import com.ossy.noteapphybrid.model.NoteEntity
import kotlinx.coroutines.flow.Flow


class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: Flow<List<NoteEntity>> = noteDao.getAllNotes()

    suspend fun insert(note: NoteEntity) {
        noteDao.insertNote(note)
    }

    suspend fun update(note: NoteEntity) {
        noteDao.updateNote(note)
    }

    suspend fun delete(note: NoteEntity) {
        noteDao.deleteNote(note)
    }

    suspend fun getNoteById(noteId: String): NoteEntity? {
        return noteDao.getNoteById(noteId)
    }

}

