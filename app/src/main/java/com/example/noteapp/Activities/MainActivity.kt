package com.example.noteapp.Activities

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.Adapters.NoteClickDeleteInterface
import com.example.noteapp.Adapters.NoteClickInterface
import com.example.noteapp.Adapters.NoteTvAdapter
import com.example.noteapp.R
import com.example.noteapp.Room.Note
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NoteClickDeleteInterface,NoteClickInterface {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addFav.setOnClickListener(){
          val intent = Intent(this@MainActivity,AddNote::class.java )
            startActivity(intent)

        }
        val rv = binding.recyclerView
        rv.layoutManager =LinearLayoutManager(this)

        val noteRV = NoteTvAdapter(this,this,this)
        rv.adapter = noteRV

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]

        viewModel.allNotes.observe(this, Observer {  list ->
            list?.let{
                noteRV.updateList(it)
            }

        })
//        viewModel = ViewModelProvider(
//            this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)
//                .get(NoteViewModel)
//        )
    }

    override fun onDeleteIconClick(note: Note) {
        val builder = AlertDialog.Builder(this@MainActivity)

        builder
//            .setIcon(R.drawable.ic_baseline_delete_outline_24)
            .setTitle("Are you sure you want to delete ${note.noteTitle} note ?")
            .setMessage("Deleting this means this individual note will be permanently deleted.")
            .setCancelable(true)
            .setPositiveButton("Yes, Delete"){dialog, id ->
                viewModel.deleteNote(note)
                Toast.makeText(this,"You have deleted ${note.noteTitle}", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No"){ dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()



    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity, AddNote::class.java)
        intent.putExtra("recyclerTitle", note.noteTitle)
        intent.putExtra("recyclerText", note.noteDescription)
        startActivity(intent)
        //Toast.makeText(this, "You have clicked ${note.noteTitle}", Toast.LENGTH_SHORT).show()
    }
}