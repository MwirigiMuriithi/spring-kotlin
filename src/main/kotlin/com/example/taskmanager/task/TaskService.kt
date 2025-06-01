// src/main/kotlin/com/example/taskmanager/task/TaskService.kt
package com.example.taskmanager.task

import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val taskMapper: TaskMapper
) {
    // Retrieve all tasks
    fun getAllTasks(): List<TaskDTO> =
        taskRepository.findAll().map { taskMapper.toDto(it) }

    // Retrieve a single task by ID
    fun getTaskById(id: Long): TaskDTO =
        taskRepository.findById(id)
            .map { taskMapper.toDto(it) }
            .orElseThrow { NoSuchElementException("Task not found with id: $id") }

    // Create a new task
    fun createTask(taskDTO: TaskDTO): TaskDTO {
        val task = taskMapper.toEntity(taskDTO)
        return taskMapper.toDto(taskRepository.save(task))
    }

    // Update an existing task
    fun updateTask(id: Long, taskDTO: TaskDTO): TaskDTO {
        if (!taskRepository.existsById(id)) {
            throw NoSuchElementException("Task not found with id: $id")
        }
        val taskToUpdate = taskMapper.toEntity(taskDTO).apply { this.id = id }
        return taskMapper.toDto(taskRepository.save(taskToUpdate))
    }

    // Delete a task by ID
    fun deleteTask(id: Long) {
        if (!taskRepository.existsById(id)) {
            throw NoSuchElementException("Task not found with id: $id")
        }
        taskRepository.deleteById(id)
    }
}
