package com.kodego.app.todoapp

interface TaskItemClickListener
{
    fun editTaskItem(taskItem: TaskItem)
    fun completeTaskItem(taskItem: TaskItem)

    //to delete
    fun deleteTaskItem(taskItem: TaskItem)
}