package com.kodego.app.todoapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kodego.app.todoapp.TaskItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskItemDao
{
      @Query("SELECT * FROM task_item_table ORDER BY id ASC")
      fun allTaskItems(): Flow<List<TaskItem>>

      @Insert(onConflict = OnConflictStrategy.REPLACE)
      suspend fun insertTaskItem(taskItem: TaskItem)

      @Update
      suspend fun updateTaskItem(taskItem: TaskItem)

      @Delete
      suspend fun deleteTaskItem(taskItem: TaskItem)
}
/**Database Access Objects are commonly known as DAOs.
 * DAOs are objects that provide access to your app’s data,
 * and they are what make Room so powerful since they abstract most of the complexity of
 * communicating to the actual database. Using DAOs instead of query builders or direct
 * queries makes it very easy to interact with your database. You avoid all the hardship
 * of debugging query builders, if something breaks, and we all know how tricky SQL can be!
 * They also provide a better separation of concerns to create a more structured application
 * and improve its testability.*/
