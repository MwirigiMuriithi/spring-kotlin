// src/main/kotlin/com/example/taskmanager/user/UserDTO.kt
package com.example.taskmanager.user

// Data Transfer Object for User; used in API requests/responses
data class UserDTO(
    val id: Long = 0,
    val username: String,
    val email: String,
    val role: String
)