// src/main/kotlin/com/example/taskmanager/task/TaskDTO.kt
package com.example.taskmanager.task

data class TaskDTO(
    val id: Long = 0,
    val title: String,
    val description: String,
    // val status: TaskStatus = TaskStatus.TODO
)