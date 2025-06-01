// src/main/kotlin/com/example/taskmanager/user/UserService.kt
package com.example.taskmanager.user

import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) {
    // Retrieve all users
    fun getAllUsers(): List<UserDTO> =
        userRepository.findAll().map { userMapper.toDto(it) }

    // Retrieve a single user by ID
    fun getUserById(id: Long): UserDTO =
        userRepository.findById(id)
            .map { userMapper.toDto(it) }
            .orElseThrow { NoSuchElementException("User not found with id: $id") }

    // Create a new user
    fun createUser(userDTO: UserDTO): UserDTO {
        val user = userMapper.toEntity(userDTO)
        return userMapper.toDto(userRepository.save(user))
    }

    // Update an existing user
    fun updateUser(id: Long, userDTO: UserDTO): UserDTO {
        if (!userRepository.existsById(id)) {
            throw NoSuchElementException("User not found with id: $id")
        }
        val userToUpdate = userMapper.toEntity(userDTO).apply { this.id = id }
        return userMapper.toDto(userRepository.save(userToUpdate))
    }

    // Delete a user by ID
    fun deleteUser(id: Long) {
        if (!userRepository.existsById(id)) {
            throw NoSuchElementException("User not found with id: $id")
        }
        userRepository.deleteById(id)
    }
}
