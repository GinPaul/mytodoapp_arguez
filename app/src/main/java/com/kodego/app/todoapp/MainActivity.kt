package com.kodego.app.todoapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.app.todoapp.databinding.ActivityMainBinding
import com.kodego.app.todoapp.db.ToDo
import com.kodego.app.todoapp.db.ToDoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), TaskItemClickListener {

    lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel
    lateinit var toDoDB : ToDoDatabase
    lateinit var adapter : TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        binding.newTaskButton.setOnClickListener{
            NewTaskSheet(null).show(supportFragmentManager, "newTaskTag")
        }

        setRecyclerView()

        toDoDB = ToDoDatabase.invoke(this)

//        //display table data on screen
//        view()

    }

    private fun setRecyclerView() {

        val mainActivity = this
        taskViewModel.taskItems.observe(this){
            binding.todoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskItemAdapter(it, mainActivity)
            }
        }

    }

    override fun editTaskItem(taskItem: TaskItem) {
        NewTaskSheet(taskItem).show(supportFragmentManager, "newTaskTag")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun completeTaskItem(taskItem: TaskItem) {
        taskViewModel.setCompleted(taskItem)
    }

//    private fun view() {
//        lateinit var toDoList: MutableList<ToDo>
//        GlobalScope.launch(Dispatchers.IO) {
//            toDoList = toDoDB.getToDo().getAllToDo()
//
//            withContext(Dispatchers.Main){
//                adapter = TaskItemAdapter(toDoList)
//                binding.todoListRecyclerView.adapter = adapter
//                binding.todoListRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
//
//                adapter.onItemDelete = { item:ToDo, position: Int ->
//
//                    delete(item)
//                    adapter..removeAt(position)
//                    adapter.notifyDataSetChanged()
//                }
//                adapter.onUpdate =  { item:Employee, position: Int ->
//
//                    showUpdateDialog(item.id)
//                    adapter.notifyDataSetChanged()
//                }
//
//            }
//        }
}