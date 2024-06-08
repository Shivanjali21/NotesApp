package com.practice.notesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Notes")
data class Notes(
  @PrimaryKey
  var id: Int? = null,
  val title: String,
  val subTitle: String,
  val notes: String,
  val date: String,
  val priority: String,
) : Serializable
