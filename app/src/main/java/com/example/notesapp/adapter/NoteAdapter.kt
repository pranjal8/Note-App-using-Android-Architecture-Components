package com.example.notesapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.UI.HomeFragmentDirections
import com.example.notesapp.databinding.NoteLayoutAdapterBinding
import com.example.notesapp.model.Note
import java.util.*


class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemBinding : NoteLayoutAdapterBinding):
        RecyclerView.ViewHolder(itemBinding.root) {

    }

    var binding : NoteLayoutAdapterBinding?= null

    private val differCallback = object : DiffUtil.ItemCallback<Note>() {

        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
         return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
           return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
            binding =NoteLayoutAdapterBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent,
                false
            )
        return NoteViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       var currentNote = differ.currentList[position]

        holder.itemView.apply {
            binding?.tvNoteTitle?.text = currentNote.noteTitle
            binding?.tvNoteBody?.text = currentNote.noteBody

            val random = Random()
            val color= Color.argb(255,
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255)
            )
            binding?.coloredNote?.setBackgroundColor(color)

        }.setOnClickListener { mView ->

            val direction = HomeFragmentDirections.actionHomeFragmentToUpdatedNewsFragment2(currentNote)
            mView.findNavController().navigate(direction)
        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}