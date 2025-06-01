// src/main/kotlin/com/example/taskmanager/auth/AuthController.kt
package com.example.taskmanager.auth

import com.example.taskmanager.user.RegisterRequest
import com.example.taskmanager.user.LoginRequest
import com.example.taskmanager.user.UserDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid
import com.example.taskmanager.user.AuthResponse


@RestController
@RequestMapping("/api/auth")
@Validated
class AuthController(private val authService: AuthService) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterRequest): ResponseEntity<UserDTO> {
        val createdUser = authService.register(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser)
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<AuthResponse> {
        val authResponse = authService.login(request)
        return ResponseEntity.ok(authResponse)
    }
}
