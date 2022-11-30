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