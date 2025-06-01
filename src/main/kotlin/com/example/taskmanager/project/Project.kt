// src/main/kotlin/com/example/taskmanager/project/Project.kt
package com.example.taskmanager.project

import jakarta.persistence.*

@Entity
@Table(name = "projects")
data class Project(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,               // Primary key
    var name: String = "",          // Project name
    var description: String = ""    // Project description
)
