// src/main/kotlin/com/example/taskmanager/task/Task.kt
package com.example.taskmanager.task

import jakarta.persistence.*

@Entity
@Table(name = "tasks")
data class Task(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,           // Primary key
    var title: String = "",     // Task title
    var description: String = ""// Task description
)
