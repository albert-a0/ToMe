package com.albertforapps.tome

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
class TodoData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String
)