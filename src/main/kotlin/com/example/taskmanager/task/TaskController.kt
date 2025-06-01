// src/main/kotlin/com/example/taskmanager/task/TaskController.kt
package com.example.taskmanager.task

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tasks")
class TaskController(private val taskService: TaskService) {

    // Get all tasks
    @GetMapping
    fun getAllTasks(): List<TaskDTO> =
        taskService.getAllTasks()

    // Get task by ID
    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): TaskDTO =
        taskService.getTaskById(id)

    // Create a new task
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTask(@RequestBody taskDTO: TaskDTO): TaskDTO =
        taskService.createTask(taskDTO)

    // Update an existing task
    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody taskDTO: TaskDTO): TaskDTO =
        taskService.updateTask(id, taskDTO)

    // Delete a task
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTask(@PathVariable id: Long) {
        taskService.deleteTask(id)
    }
}
