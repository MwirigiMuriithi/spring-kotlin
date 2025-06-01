// src/main/kotlin/com/example/taskmanager/user/LoginRequest.kt
package com.example.taskmanager.user

import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @field:NotBlank
    val username: String,

    @field:NotBlank
    val password: String
)
