// src/main/kotlin/com/example/taskmanager/user/AuthResponse.kt
package com.example.taskmanager.user

data class AuthResponse(
    val token: String,
    val tokenType: String = "Bearer"
)
