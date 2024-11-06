package com.example.ulzhana_midterm_taskmanagerapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ulzhana_midterm_taskmanagerapp.data.Task

class MainActivity : AppCompatActivity() {

    private val taskList = mutableListOf<Task>()
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Lifecycle", "onCreate called")

        recyclerView = findViewById(R.id.recyclerViewTasks)

        taskAdapter = TaskAdapter(
            taskList,
            onUpdateClick = { task -> showUpdateTaskDialog(task) },
            onDeleteClick = { task -> deleteTask(task) }
        )

        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val buttonAddTask: Button = findViewById(R.id.buttonAddTask)
        buttonAddTask.setOnClickListener {
            showAddTaskDialog()
        }
    }

    private fun showAddTaskDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_task, null)
        val taskTitleInput: EditText = dialogView.findViewById(R.id.editTextTaskTitle)
        val taskDescriptionInput: EditText = dialogView.findViewById(R.id.editTextTaskDescription)

        val dialog = android.app.AlertDialog.Builder(this)
            .setTitle("Add New Task")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val title = taskTitleInput.text.toString()
                val description = taskDescriptionInput.text.toString()
                if (title.isNotEmpty()) {
                    addTaskToList(title, description)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

    private fun addTaskToList(title: String, description: String) {
        val newTask = Task(id = taskList.size + 1, title = title, description = description)
        taskList.add(newTask)
        taskAdapter.notifyDataSetChanged()
    }

    private fun showUpdateTaskDialog(task: Task) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_task, null)
        val taskTitleInput: EditText = dialogView.findViewById(R.id.editTextTaskTitle)
        val taskDescriptionInput: EditText = dialogView.findViewById(R.id.editTextTaskDescription)

        taskTitleInput.setText(task.title)
        taskDescriptionInput.setText(task.description)

        val dialog = android.app.AlertDialog.Builder(this)
            .setTitle("Update Task")
            .setView(dialogView)
            .setPositiveButton("Update") { _, _ ->
                val title = taskTitleInput.text.toString()
                val description = taskDescriptionInput.text.toString()
                if (title.isNotEmpty()) {
                    updateTask(task, title, description)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

    private fun updateTask(task: Task, newTitle: String, newDescription: String) {
        task.title = newTitle
        task.description = newDescription
        taskAdapter.notifyDataSetChanged()
    }

    private fun deleteTask(task: Task) {
        taskList.remove(task)
        taskAdapter.notifyDataSetChanged()
    }
}
