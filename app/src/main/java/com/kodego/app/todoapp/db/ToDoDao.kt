package com.kodego.app.todoapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ToDoDao {

    @Insert
    fun addToDo(toDo: ToDo)

    @Query("SELECT * FROM ToDo")
    fun getAllToDo():MutableList<ToDo>

    @Query("UPDATE ToDo SET name = :name WHERE id = :id")
    fun updateToDo(name:String,id:Int)

    @Query("DELETE FROM ToDo WHERE id = :id")
    fun deletetoDo(id:Int)
}