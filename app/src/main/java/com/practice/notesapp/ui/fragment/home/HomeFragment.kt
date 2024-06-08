package com.practice.notesapp.ui.fragment.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.practice.notesapp.R
import com.practice.notesapp.databinding.FragmentHomeBinding
import com.practice.notesapp.model.Notes
import com.practice.notesapp.viewmodel.NotesViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding:FragmentHomeBinding
    private val notesViewModel : NotesViewModel by viewModels()
    private val homeAdapter: HomeAdapter by lazy {
      HomeAdapter()
    }
    private var myNotes = arrayListOf<Notes>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        setHasOptionsMenu(true)

        binding.apply {
            fabAdd.setOnClickListener {
              val actionCreate = HomeFragmentDirections.actionHomeFragmentToCreateNoteFragment()
              findNavController().navigate(actionCreate)
            }

           ivFilter.setOnClickListener {
               notesViewModel.getNotes().observe(viewLifecycleOwner) {
                   binding.apply {
                       rvNotes.apply {
                           adapter = homeAdapter
                           homeAdapter.asyncDiffUtil.submitList(it)
                       }
                   }
               }
           }

            mtvHigh.setOnClickListener {
                notesViewModel.getHighNotes().observe(viewLifecycleOwner) {
                    myNotes = it as ArrayList<Notes>
                    binding.apply {
                        rvNotes.apply {
                            adapter = homeAdapter
                            homeAdapter.asyncDiffUtil.submitList(it)
                        }
                    }
                }
            }

            mtvMedium.setOnClickListener {
                notesViewModel.getMediumNotes().observe(viewLifecycleOwner) {
                    myNotes = it as ArrayList<Notes>
                    binding.apply {
                        rvNotes.apply {
                            adapter = homeAdapter
                            homeAdapter.asyncDiffUtil.submitList(it)
                        }
                    }
                }
            }

            mtvLow.setOnClickListener {
                notesViewModel.getLowNotes().observe(viewLifecycleOwner) {
                    myNotes = it as ArrayList<Notes>
                    binding.apply {
                        rvNotes.apply {
                            adapter = homeAdapter
                            homeAdapter.asyncDiffUtil.submitList(it)
                        }
                    }
                }
            }
        }

       notesViewModel.getNotes().observe(viewLifecycleOwner) {
          myNotes = it as ArrayList<Notes>
          binding.apply {
             rvNotes.apply {
               adapter = homeAdapter
               homeAdapter.asyncDiffUtil.submitList(it)
             }
          }
       }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val itemMenu = menu.findItem(R.id.menuSearch)
        val searchView= itemMenu.actionView as SearchView
        searchView.queryHint = "Search Notes.."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
              return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
              notesSearchFilter(newText)
              return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesSearchFilter(newText: String?) {
        val newFilteredList = arrayListOf<Notes>()
        for (i in myNotes) {
            if (i.title.contains(newText!!) || i.subTitle.contains(newText)) {
                newFilteredList.add(i)
            }
        }
        homeAdapter.asyncDiffUtil.submitList(newFilteredList)
    }
}