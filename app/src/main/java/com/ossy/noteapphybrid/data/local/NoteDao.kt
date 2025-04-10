package com.ossy.noteapphybrid.data.local


import androidx.room.*
import com.ossy.noteapphybrid.model.NoteEntity
import kotlinx.coroutines.flow.Flow

//This interface defines the database operations.
@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("SELECT * FROM notes ORDER BY timestamp DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>


    @Query("SELECT * FROM notes WHERE noteId = :noteId LIMIT 1")
    suspend fun getNoteById(noteId: String): NoteEntity?
}

