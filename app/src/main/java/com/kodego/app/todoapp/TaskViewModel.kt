package com.kodego.app.todoapp


import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.time.LocalDate


class TaskViewModel(private val repository: TaskItemRepository): ViewModel()
{
    val taskItems: LiveData<List<TaskItem>> = repository.allTaskItems.asLiveData()

    fun addTaskItem(taskItem: TaskItem) = viewModelScope.launch {
        repository.insertTaskItem(taskItem)
    }

    fun updateTaskItem(taskItem: TaskItem) = viewModelScope.launch {
        repository.updateTaskItem(taskItem)
    }

    fun setCompleted(taskItem: TaskItem) = viewModelScope.launch {
        if (!taskItem.isCompleted())
            taskItem.completedDateString = TaskItem.dateFormatter.format(LocalDate.now())
        repository.updateTaskItem(taskItem)
    }

    //to delete
    fun deleteTaskItem(taskItem: TaskItem) = viewModelScope.launch {
        repository.deleteTaskItem(taskItem)
    }
}

class TaskItemModelFactory(private val repository: TaskItemRepository) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java))
            return TaskViewModel(repository) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
/**The ViewModel class is designed to hold and manage UI-related data in a
 * life-cycle conscious way. This allows data to survive configuration changes such as
 * screen rotations.*/

/**A ViewModelScope is defined for each ViewModel in your app.
 * Any coroutine launched in this scope is automatically canceled if the ViewModel is cleared.
 * Coroutines are useful here for when you have work that needs to be done only if the ViewModel
 * is active. For example, if you are computing some data for a layout, you should scope the work
 * to the ViewModel so that if the ViewModel is cleared, the work is canceled automatically to
 * avoid consuming resources.*/


/**A ViewModelFactory allows us to pass certain values in the ViewModel whenever it is initialized.
 * In our case, we need to pass a RequestQueue in the ViewModel’s constructor.*/
