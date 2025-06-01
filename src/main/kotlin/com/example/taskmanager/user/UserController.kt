// src/main/kotlin/com/example/taskmanager/user/UserController.kt
package com.example.taskmanager.user

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    // Get all users
    @GetMapping
    fun getAllUsers(): List<UserDTO> =
        userService.getAllUsers()

    // Get user by ID
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): UserDTO =
        userService.getUserById(id)

    // Create a new user
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody userDTO: UserDTO): UserDTO =
        userService.createUser(userDTO)

    // Update an existing user
    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody userDTO: UserDTO): UserDTO =
        userService.updateUser(id, userDTO)

    // Delete a user
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteUser(id)
    }
}
