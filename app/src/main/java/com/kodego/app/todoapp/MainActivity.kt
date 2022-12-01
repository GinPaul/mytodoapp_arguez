package com.kodego.app.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.app.todoapp.databinding.ActivityMainBinding
import com.kodego.app.todoapp.db.TodoApplication
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), TaskItemClickListener
{
    lateinit var simpleDateFormat: SimpleDateFormat
    lateinit var date: String
    lateinit var textView: TextView
    lateinit var calendar: Calendar

    private lateinit var binding: ActivityMainBinding
    //private lateinit var taskViewModel: TaskViewModel
    private val taskViewModel: TaskViewModel by viewModels {
        TaskItemModelFactory((application as TodoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        binding.newTaskButton.setOnClickListener {
            NewTaskSheet(null).show(supportFragmentManager, "newTaskTag")
            Toast.makeText(applicationContext,"Creating a new task.", Toast.LENGTH_SHORT).show()
        }
        setRecyclerView()

        //showing current date and time
        textView = binding.tvDateToday
        calendar = Calendar.getInstance()
        simpleDateFormat = SimpleDateFormat("EEE | MMM dd, yyyy")
        date = simpleDateFormat.format(calendar.time)
        textView.text = date
    }

    private fun setRecyclerView()
    {
        val mainActivity = this
        taskViewModel.taskItems.observe(this){
            binding.todoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskItemAdapter(it, mainActivity)
            }
        }
    }

    override fun editTaskItem(taskItem: TaskItem)
    {
        NewTaskSheet(taskItem).show(supportFragmentManager,"newTaskTag")
        Toast.makeText(applicationContext,"Edit this task.", Toast.LENGTH_SHORT).show()
    }

    override fun completeTaskItem(taskItem: TaskItem)
    {
        taskViewModel.setCompleted(taskItem)
        Toast.makeText(applicationContext,"Task marked as completed.", Toast.LENGTH_SHORT).show()
    }

    override fun deleteTaskItem(taskItem: TaskItem)
    {
        taskViewModel.deleteTaskItem(taskItem)
        Toast.makeText(applicationContext,"Task is deleted!", Toast.LENGTH_SHORT).show()
    }


}