package com.practice.notesapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.practice.notesapp.model.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNotes(notes: Notes)

    @Query("SELECT * FROM Notes WHERE priority=3")
    fun getHighNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=2")
    fun getMediumNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=1")
    fun getLowNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<Notes>>

    @Query("DELETE FROM Notes WHERE id = :id")
    fun deleteNote(id:Int)

    @Update
    fun updateNote(notes: Notes)
}