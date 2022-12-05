package com.kodego.app.todoapp

import androidx.annotation.WorkerThread
import com.kodego.app.todoapp.db.TaskItemDao
import kotlinx.coroutines.flow.Flow

class TaskItemRepository(private val taskItemDao: TaskItemDao)
{
    val allTaskItems: Flow<List<TaskItem>> = taskItemDao.allTaskItems()

    //to update
    @WorkerThread
    suspend fun insertTaskItem(taskItem: TaskItem)
    {
        taskItemDao.insertTaskItem(taskItem)
    }

    //to update
    @WorkerThread
    suspend fun updateTaskItem(taskItem: TaskItem)
    {
        taskItemDao.updateTaskItem(taskItem)
    }

    //to delete
    @WorkerThread
    suspend fun deleteTaskItem(taskItem: TaskItem)
    {
        taskItemDao.deleteTaskItem(taskItem)
    }

}
/**A repository is commonly regarded as the single source of truth in an Android application.
 * In other words, it acts as an abstraction over a particular data source.
 * A repository enables an application to consume data without worrying about its origin.
 *
 * The repository class will be responsible for interacting with the Room database on
 * behalf of the ViewModel and will need to provide methods that use the DAO to insert,
 * delete and query product records. With the exception of the getAllProducts() DAO method
 * (which returns a LiveData object) these database operations will need to be performed on
 * separate threads from the main thread using the AsyncTask class.
 *
 *Suspending functions are at the center of everything coroutines.
 * A suspending function is simply a function that can be paused and resumed at a later time.
 * They can execute a long running operation and wait for it to complete without blocking.
 * The syntax of a suspending function is similar to that of a regular function except for
 * the addition of the suspend keyword. It can take a parameter and have a return type.
 * However, suspending functions can only be invoked by another suspending function or
 * within a coroutine.*/