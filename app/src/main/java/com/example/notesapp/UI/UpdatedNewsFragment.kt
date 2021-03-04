package com.example.notesapp.UI

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.MainActivity
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentUpdatedNewsBinding
import com.example.notesapp.model.Note
import com.example.notesapp.toast
import com.example.notesapp.viewmodel.NoteViewModel
import com.google.android.material.snackbar.Snackbar


class UpdatedNewsFragment : Fragment(R.layout.fragment_updated_news) {
    private var _binding: FragmentUpdatedNewsBinding ? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    //Used to get the passed arguments
    private val args: UpdatedNewsFragmentArgs by navArgs()

    // give reference of table
    private lateinit var currentNote: Note

    private lateinit var noteViewModel : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding= FragmentUpdatedNewsBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel
        currentNote = args.note!!

        binding.etNoteBodyUpdate.setText(currentNote.noteBody)
        binding.etNoteTitleUpdate.setText(currentNote.noteTitle)

        binding.fabDone.setOnClickListener {
            val title = binding.etNoteTitleUpdate.text.toString().trim()
            val body = binding.etNoteBodyUpdate.text.toString().trim()

            if (title.isNotEmpty()) {
                val note = Note(currentNote.id, title, body)
                noteViewModel.updateNote(note)

                activity?.toast("Note updated")

                view.findNavController().navigate(R.id.action_updatedNewsFragment2_to_homeFragment)

            } else {
                activity?.toast("Enter a note title please")
            }
        }
    }

    private fun deleteNote(currentNote: Note) {

                noteViewModel.deleteNote(currentNote)
                activity?.toast("Note delete successfully",)

                view?.findNavController()?.navigate(
                        R.id.action_updatedNewsFragment2_to_homeFragment
                )
            }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_update_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                deleteNote(currentNote)
            }
        }

        return super.onOptionsItemSelected(item)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
