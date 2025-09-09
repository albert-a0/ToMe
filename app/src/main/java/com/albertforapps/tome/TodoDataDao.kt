package com.albertforapps.tome

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TodoDataDao {

    @Query("SELECT * FROM todos")
    fun getAll(): List<TodoData>

    @Query("SELECT * FROM todos WHERE id = :id")
    fun getById(id: Int): TodoData

    @Query("INSERT INTO todos (title, description) VALUES (:title, :description)")
    fun insert(title: String, description: String)

    @Query("DELETE FROM todos WHERE id = :id")
    fun delete(id: Int)

}