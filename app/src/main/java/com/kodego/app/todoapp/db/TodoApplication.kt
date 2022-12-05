package com.kodego.app.todoapp.db

import android.app.Application
import com.kodego.app.todoapp.TaskItemRepository

class TodoApplication : Application()
{
    private val database by lazy { TaskItemDatabase.getDatabase(this) }
    val repository by lazy { TaskItemRepository(database.taskItemDao()) }
}

/**The Application class in Android is the base class within an Android app that contains
all other components such as activities and services. The Application class, or any subclass
of the Application class, is instantiated before any other class when the process for your
application/package is created.

This class is primarily used for initialization of global state before the first Activity
is displayed. Note that custom Application objects should be used carefully and are often
not needed at all.*/

