package com.albertforapps.tome

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDataDao {

    @Query("SELECT * FROM todos ORDER BY id DESC")
    fun getAll(): List<TodoData>

    @Query("SELECT * FROM todos WHERE id = :id")
    fun getById(id: Int): TodoData

    @Query("INSERT INTO todos (title, description) VALUES (:title, :description)")
    fun insert(title: String, description: String)

    @Query("DELETE FROM todos WHERE id = :id")
    fun delete(id: Int)

    @Query("UPDATE todos SET title = :title, description = :description WHERE id = :id")
    fun update(id: Int, title: String, description: String)


}