package com.example.noteapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.R
import com.example.noteapp.Room.Note
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.databinding.ActivityAddNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class AddNote : AppCompatActivity() {
    private lateinit var  binding: ActivityAddNoteBinding



    lateinit var viewModel: NoteViewModel
    var noteID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

//

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]

        // on below line we are getting data passed via an intent.
        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            // on below line we are setting data to edit text.
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteId", -1)
            binding.apply {
                idBtn.setText("Update Note")
                idEdtNoteName.setText(noteTitle)
                idEdtNoteDesc.setText(noteDescription)
            }

        } else {
            binding.idBtn.setText("Save Note")
        }

        // on below line we are adding
        // click listener to our save button.
        val btn =  binding.idBtn
        val nTitle = binding.idEdtNoteName
        val nBody = binding.idEdtNoteDesc
        btn.setOnClickListener {
            if (nTitle.text.toString().isEmpty() && nBody.text.toString().isEmpty()){
                binding.apply {
                    nTitle.error = "Title required"
                    nBody.error = "Body require"
                }
            }
            else{
                // on below line we are getting
                // title and desc from edit text.
                val noteTitle = nTitle.text.toString()
                val noteDescription = nBody.text.toString()
                // on below line we are checking the type
                // and then saving or updating the data.
                if (noteType.equals("Edit")) {
                    if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                        val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                        val currentDateAndTime: String = sdf.format(Date())
                        val updatedNote = Note(noteTitle, noteDescription, currentDateAndTime)
                        updatedNote.id = noteID
                        viewModel.updateNote(updatedNote)
                        Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                    }
                } else {
                    if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                        val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                        val currentDateAndTime: String = sdf.format(Date())
                        // if the string is not empty we are calling a
                        // add note method to add data to our room database.
                        viewModel.addNote(Note(noteTitle, noteDescription, currentDateAndTime))
                        Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                    }

                }

            }


             //opening the new activity on below line
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}