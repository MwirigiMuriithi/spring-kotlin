// src/main/kotlin/com/example/taskmanager/task/TaskRepository.kt
package com.example.taskmanager.task

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Long>
