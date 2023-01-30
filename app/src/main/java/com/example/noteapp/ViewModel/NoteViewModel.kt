package com.example.noteapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapp.Repository.NoteRepository
import com.example.noteapp.Room.Note
import com.example.noteapp.Room.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {
    val allNotes : LiveData<List<Note>>
    val repository :NoteRepository

    init {
        val dao =NoteDatabase.getDatabase(application).getNotesDAO()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }
    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
    fun deleteNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    //add update
}