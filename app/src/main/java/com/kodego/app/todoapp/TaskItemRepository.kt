package com.kodego.app.todoapp

import androidx.annotation.WorkerThread
import com.kodego.app.todoapp.db.TaskItemDao
import kotlinx.coroutines.flow.Flow

class TaskItemRepository(private val taskItemDao: TaskItemDao)
{
    val allTaskItems: Flow<List<TaskItem>> = taskItemDao.allTaskItems()

    @WorkerThread
    suspend fun insertTaskItem(taskItem: TaskItem)
    {
        taskItemDao.insertTaskItem(taskItem)
    }

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
 * A repository enables an application to consume data without worrying about its origin.*/