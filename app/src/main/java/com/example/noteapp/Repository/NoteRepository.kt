package com.example.noteapp.Repository

import androidx.lifecycle.LiveData
import com.example.noteapp.Room.Note
import com.example.noteapp.Room.NotesDAO

class NoteRepository(private  val notesDAO: NotesDAO) {
    val allNotes:LiveData<List<Note>> = notesDAO.getAllNotes()

    suspend fun insert(note: Note){
        notesDAO.insertNote(note)
    }
    suspend fun  delete(note: Note){
        notesDAO.deleteNote(note)
    }
    suspend fun  update(note: Note){
        notesDAO.updateNote(note)
    }
}