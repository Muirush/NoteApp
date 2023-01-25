package com.example.noteapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.Room.Note
import com.google.android.material.snackbar.Snackbar

class NoteTvAdapter(
    val context: Context,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface

):  RecyclerView.Adapter<NoteTvAdapter.ViewHolder>() {


    private  val allNotesRV = ArrayList<Note>()

   inner class ViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView) {
       val noteTv = itemView.findViewById<TextView>(R.id.idTVNote)
       val dateTv = itemView.findViewById<TextView>(R.id.idTVDate)
       val noteBody = itemView.findViewById<TextView>(R.id.idTVNoteB)
       val deleteIv = itemView.findViewById<ImageView>(R.id.idIVDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item_view, parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //check Get
        holder.noteTv.text = allNotesRV[position].noteTitle
        holder.noteBody.text = allNotesRV[position].noteDescription
        holder.dateTv.text = "Last updated on: "+ allNotesRV[position].timeStamp

        holder.deleteIv.setOnClickListener(){
            noteClickDeleteInterface.onDeleteIconClick(allNotesRV[position])
        }
        holder.itemView.setOnClickListener(){
            noteClickInterface.onNoteClick(allNotesRV[position])
        }
//        holder.itemView.setOnLongClickListener(){
//            noteLongClickInterface.onLongNoteClick(allNotesRV[position])
//        }




    }

    override fun getItemCount(): Int {
       return allNotesRV.size
    }
    fun updateList(newList: List<Note>){
        allNotesRV.clear()
        allNotesRV.addAll(newList)
        this.notifyDataSetChanged()
    }

}

interface NoteLongClickInterface {
    fun  onLongNoteClick(note: Note)
}

interface  NoteClickDeleteInterface{
    fun onDeleteIconClick(note: Note)

}
interface  NoteClickInterface{
    fun  onNoteClick(note: Note)

}




