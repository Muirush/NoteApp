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
    // var noteID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]
        binding.idBtn.setOnClickListener(){
            createNote()
        }

    }

    private fun createNote() {
        val title = binding.idEdtNoteName.text.toString().trim()
        val body = binding.idEdtNoteDesc.text.toString().trim()
        if (title.isEmpty()||body.isEmpty()){
            binding.apply {
                //idBtn.isEnabled =false
                idEdtNoteName.error ="Cannot be empty"
                idEdtNoteDesc.error = "Cannot be empty"
            }
        }
        if (title.isNotEmpty() && body.isNotEmpty()) {

            val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
            val currentDateAndTime: String = sdf.format(Date())
            // if the string is not empty we are calling a
            // add note method to add data to our room database.
            viewModel.addNote(Note(title, body, currentDateAndTime))
            Toast.makeText(this, "$title Added", Toast.LENGTH_LONG).show()

            val intent = Intent(this@AddNote,MainActivity::class.java )
            startActivity(intent)
            finish()

        }

    }
}