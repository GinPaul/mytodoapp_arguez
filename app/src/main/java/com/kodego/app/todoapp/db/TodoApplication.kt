package com.kodego.app.todoapp.db

import android.app.Application
import com.kodego.app.todoapp.TaskItemRepository

class TodoApplication : Application()
{
    private val database by lazy { TaskItemDatabase.getDatabase(this) }
    val repository by lazy { TaskItemRepository(database.taskItemDao()) }
}