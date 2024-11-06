package com.example.ulzhana_midterm_taskmanagerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulzhana_midterm_taskmanagerapp.data.Task

class TaskAdapter(
    private val taskList: MutableList<Task>,
    private val onUpdateClick: (Task) -> Unit,
    private val onDeleteClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)

        // Update button click listener
        holder.buttonUpdate.setOnClickListener {
            onUpdateClick(task)
        }

        // Delete button click listener
        holder.buttonDelete.setOnClickListener {
            onDeleteClick(task)
        }
    }

    override fun getItemCount(): Int = taskList.size

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTaskTitle)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textViewTaskDescription)
        val buttonUpdate: Button = itemView.findViewById(R.id.buttonUpdateTask)
        val buttonDelete: Button = itemView.findViewById(R.id.buttonDeleteTask)

        fun bind(task: Task) {
            titleTextView.text = task.title
            descriptionTextView.text = task.description
        }
    }
}
