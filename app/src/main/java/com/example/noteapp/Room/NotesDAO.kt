package com.example.noteapp.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote (note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("Select * from notesTable order  by id DESC")
    fun getAllNotes():LiveData<List<Note>>

    @Update
    suspend fun  updateNote(note: Note)
}