// src/main/kotlin/com/example/taskmanager/auth/AuthService.kt
package com.example.taskmanager.auth

import com.example.taskmanager.user.*
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import jakarta.transaction.Transactional
import java.util.NoSuchElementException
import com.example.taskmanager.user.AuthResponse


@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils,
    private val userMapper: UserMapper
) {
    @Transactional
    fun register(request: RegisterRequest): UserDTO {
        if (userRepository.existsByUsername(request.username)) {
            throw IllegalArgumentException("Username already taken")
        }
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalArgumentException("Email already registered")
        }
        val user = User(
            username = request.username,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            role = "ROLE_USER"
        )
        val savedUser = userRepository.save(user)
        return userMapper.toDto(savedUser)
    }

    fun login(request: LoginRequest): AuthResponse {
        // Attempt authentication
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.username, request.password)
        )
        // If we reach here without exception, authentication succeeded
        val userDetails = authentication.principal as CustomUserDetails
        val token = jwtUtils.generateToken(userDetails.username)
        return AuthResponse(token)
    }
}
