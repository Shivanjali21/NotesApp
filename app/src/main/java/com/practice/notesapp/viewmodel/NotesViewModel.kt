package com.practice.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.practice.notesapp.model.Notes
import com.practice.notesapp.repository.NotesRepository
import com.practice.notesapp.room.NotesDatabase

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val notesRepository: NotesRepository

    init {
      val notesDao = NotesDatabase.getDataBase(application).notesDao()
      notesRepository = NotesRepository(notesDao)
    }

    fun addNotes(notes: Notes) {
      notesRepository.addNotes(notes)
    }

    fun getNotes() : LiveData<List<Notes>> = notesRepository.getAllNotes()
    fun getHighNotes(): LiveData<List<Notes>> = notesRepository.getHighNotes()
    fun getMediumNotes(): LiveData<List<Notes>> = notesRepository.getMediumNotes()
    fun getLowNotes(): LiveData<List<Notes>> = notesRepository.getLowNotes()

    fun deleteNote(id: Int) {
      notesRepository.deleteNote(id)
    }

    fun updateNote(notes: Notes) {
      notesRepository.updateNote(notes)
    }
}