package com.practice.notesapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.practice.notesapp.model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun notesDao() : NotesDao

    companion object {
       @Volatile
       var INSTANCE : NotesDatabase ?= null

       fun getDataBase(context: Context):NotesDatabase {
          val tempInstance = INSTANCE
          if(tempInstance != null){
             return tempInstance
          }
          synchronized(this){
            val roomDbInstance = Room.databaseBuilder(context, NotesDatabase::class.java, "Notes_DB")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build()
            INSTANCE = roomDbInstance
            return roomDbInstance
          }
       }
    }
}