// src/main/kotlin/com/example/taskmanager/task/TaskService.kt
package com.example.taskmanager.task

import com.example.taskmanager.user.User
import com.example.taskmanager.user.UserRepository
import java.util.NoSuchElementException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class TaskService(
        private val taskRepository: TaskRepository,
        private val taskMapper: TaskMapper,
        private val userRepository: UserRepository // ⬅️ Inject UserRepository
) {

    // Helper: get the currently authenticated User entity
    private fun getCurrentUser(): User {
        val username = SecurityContextHolder.getContext().authentication.name
        println("Authenticated username: $username")
        return userRepository.findByUsername(username)
                ?: throw IllegalStateException("User not found: $username")
    }

    // Retrieve all tasks belonging to the current user
    fun getAllTasks(): List<TaskDTO> {
        val user = getCurrentUser()
        return taskRepository.findAllByUser(user).map { taskMapper.toDto(it) }
    }

    // Retrieve a single task by ID, but only if it belongs to the current user
    fun getTaskById(id: Long): TaskDTO {
        val user = getCurrentUser()
        val task =
                taskRepository.findByIdAndUser(id, user)
                        ?: throw NoSuchElementException("Task not found or not owned by user: $id")
        return taskMapper.toDto(task)
    }

    // Create a new task and assign it to the current user
    fun createTask(taskDTO: TaskDTO): TaskDTO {
        val user = getCurrentUser()
        val task = taskMapper.toEntity(taskDTO).apply { this.user = user }
        return taskMapper.toDto(taskRepository.save(task))
    }

    // Update an existing task only if it belongs to the current user
    fun updateTask(id: Long, taskDTO: TaskDTO): TaskDTO {
        val user = getCurrentUser()
        val existingTask =
                taskRepository.findByIdAndUser(id, user)
                        ?: throw NoSuchElementException("Task not found or not owned by user: $id")
        val taskToUpdate =
                taskMapper.toEntity(taskDTO).apply {
                    this.id = id
                    this.user = user
                }
        return taskMapper.toDto(taskRepository.save(taskToUpdate))
    }

    // Delete a task only if it belongs to the current user
    fun deleteTask(id: Long) {
        val user = getCurrentUser()
        val task =
                taskRepository.findByIdAndUser(id, user)
                        ?: throw NoSuchElementException("Task not found or not owned by user: $id")
        taskRepository.delete(task)
    }
}
