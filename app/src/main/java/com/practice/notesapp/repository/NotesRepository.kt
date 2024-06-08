package com.practice.notesapp.repository

import androidx.lifecycle.LiveData
import com.practice.notesapp.model.Notes
import com.practice.notesapp.room.NotesDao

class NotesRepository(private val notesDao: NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>> {
       return notesDao.getNotes()
    }

    fun getHighNotes(): LiveData<List<Notes>> {
      return notesDao.getHighNotes()
    }

    fun getMediumNotes(): LiveData<List<Notes>> {
        return notesDao.getMediumNotes()
    }

    fun getLowNotes(): LiveData<List<Notes>> {
        return notesDao.getLowNotes()
    }

    fun addNotes(notes: Notes) {
      notesDao.addNotes(notes)
    }

    fun deleteNote(id: Int) {
      notesDao.deleteNote(id)
    }

    fun updateNote(notes: Notes) {
      notesDao.updateNote(notes)
    }
}