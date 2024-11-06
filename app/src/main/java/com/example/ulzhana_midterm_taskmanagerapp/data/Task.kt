package com.example.ulzhana_midterm_taskmanagerapp.data

data class Task(
    val id: Int,
    var title: String,
    var description: String,
    var isCompleted: Boolean = false
)

