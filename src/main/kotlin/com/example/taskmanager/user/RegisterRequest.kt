// src/main/kotlin/com/example/taskmanager/user/RegisterRequest.kt
package com.example.taskmanager.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RegisterRequest(
    @field:NotBlank @field:Size(min = 4, max = 20)
    val username: String,

    @field:NotBlank @field:Email
    val email: String,

    @field:NotBlank @field:Size(min = 6, max = 100)
    val password: String
)
