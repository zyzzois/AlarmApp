package com.octaneocatane.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarm_items")
data class AlarmItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val time: Int,
    val description: String
)
