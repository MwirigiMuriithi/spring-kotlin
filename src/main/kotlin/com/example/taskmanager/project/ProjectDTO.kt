// src/main/kotlin/com/example/taskmanager/project/ProjectDTO.kt
package com.example.taskmanager.project

// DTO for Project data transfer
data class ProjectDTO(
    val id: Long = 0,
    val name: String,
    val description: String
)
