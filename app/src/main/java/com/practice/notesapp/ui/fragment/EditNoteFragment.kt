package com.practice.notesapp.ui.fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.practice.notesapp.R
import com.practice.notesapp.databinding.FragmentEditNotesBinding
import com.practice.notesapp.model.Notes
import com.practice.notesapp.viewmodel.NotesViewModel
import java.util.Date

class EditNoteFragment : Fragment(R.layout.fragment_edit_notes) {

    private lateinit var binding:FragmentEditNotesBinding
    private val notesArgs : EditNoteFragmentArgs by navArgs()
    private val notesViewModel: NotesViewModel by viewModels()
    private var priority: String = "1"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditNotesBinding.bind(view)
        setHasOptionsMenu(true)

        binding.apply {
           etTitle.setText(notesArgs.data.title)
           etSubTitle.setText(notesArgs.data.subTitle)
           etNotes.setText(notesArgs.data.notes)

            when(notesArgs.data.priority) {
                "1" -> {
                    priority = "1"
                    ivEtGreenDot.setImageResource(R.drawable.ic_check)
                    ivEtRedDot.setImageResource(0)
                    ivEtYellowDot.setImageResource(0)
                }
                "2" -> {
                    priority = "2"
                    ivEtYellowDot.setImageResource(R.drawable.ic_check)
                    ivEtGreenDot.setImageResource(0)
                    ivEtRedDot.setImageResource(0)                }
                "3" -> {
                    priority = "3"
                    ivEtRedDot.setImageResource(R.drawable.ic_check)
                    ivEtGreenDot.setImageResource(0)
                    ivEtYellowDot.setImageResource(0)                }
            }

            ivEtGreenDot.setOnClickListener {
                priority = "1"
                ivEtGreenDot.setImageResource(R.drawable.ic_check)
                ivEtRedDot.setImageResource(0)
                ivEtYellowDot.setImageResource(0)
            }
            ivEtYellowDot.setOnClickListener {
                priority = "2"
                ivEtYellowDot.setImageResource(R.drawable.ic_check)
                ivEtGreenDot.setImageResource(0)
                ivEtRedDot.setImageResource(0)
            }
            ivEtRedDot.setOnClickListener {
                priority = "3"
                ivEtRedDot.setImageResource(R.drawable.ic_check)
                ivEtGreenDot.setImageResource(0)
                ivEtYellowDot.setImageResource(0)
            }

            etFab.setOnClickListener {
              updateNotes()
            }
        }
    }

    private fun updateNotes(){
        val title =  binding.etTitle.text.toString()
        val subTitle =  binding.etSubTitle.text.toString()
        val notes =  binding.etNotes.text.toString()
        val d = Date()
        val notesDate : CharSequence = DateFormat.format("MMMM d, yyyy", d.time)
        val notesData = Notes(notesArgs.data.id, title, subTitle, notes, notesDate.toString(), priority)
        notesViewModel.updateNote(notesData)
        Toast.makeText(requireContext(), "Notes updated successfully", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_editNoteFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuDelete){
            val bottomSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.layout_bottom_delete)
            val mtvYes = bottomSheet.findViewById<View>(R.id.mtvYesMsg)
            mtvYes?.setOnClickListener {
              notesViewModel.deleteNote(notesArgs.data.id!!)
              bottomSheet.dismiss()
              findNavController().navigate(R.id.action_editNoteFragment_to_homeFragment)
            }
            val mtvNo = bottomSheet.findViewById<View>(R.id.mtvNoMsg)
            mtvNo?.setOnClickListener { bottomSheet.dismiss() }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}