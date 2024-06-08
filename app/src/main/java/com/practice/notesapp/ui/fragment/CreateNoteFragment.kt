package com.practice.notesapp.ui.fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.practice.notesapp.R
import com.practice.notesapp.databinding.FragmentCreateNoteBinding
import com.practice.notesapp.model.Notes
import com.practice.notesapp.viewmodel.NotesViewModel
import java.util.Date

class CreateNoteFragment : Fragment(R.layout.fragment_create_note) {

    private lateinit var binding:FragmentCreateNoteBinding
    private var priority: String = "1"
    private val notesViewModel : NotesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateNoteBinding.bind(view)

        binding.ivGreenDot.setImageResource(R.drawable.ic_check)

        binding.apply {
           fabAdd.setOnClickListener {
             createNotes()
           }

           ivGreenDot.setOnClickListener {
              priority = "1"
              ivGreenDot.setImageResource(R.drawable.ic_check)
              ivRedDot.setImageResource(0)
              ivYellowDot.setImageResource(0)
           }
           ivYellowDot.setOnClickListener {
              priority = "2"
              ivYellowDot.setImageResource(R.drawable.ic_check)
              ivGreenDot.setImageResource(0)
              ivRedDot.setImageResource(0)
           }
           ivRedDot.setOnClickListener {
              priority = "3"
              ivRedDot.setImageResource(R.drawable.ic_check)
              ivGreenDot.setImageResource(0)
              ivYellowDot.setImageResource(0)
           }
        }
    }

    private fun createNotes(){
       val title =  binding.etTitle.text.toString()
       val subTitle =  binding.etSubTitle.text.toString()
       val notes =  binding.etNotes.text.toString()
       val d = Date()
       val notesDate : CharSequence = DateFormat.format("MMMM d, yyyy", d.time)
       val notesData = Notes(null, title, subTitle, notes, notesDate.toString(), priority)
       notesViewModel.addNotes(notesData)
       Toast.makeText(requireContext(), "Notes created successfully", Toast.LENGTH_SHORT).show()
       findNavController().navigate(R.id.action_createNoteFragment_to_homeFragment)
    }
}