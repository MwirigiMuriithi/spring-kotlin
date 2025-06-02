// src/main/kotlin/com/example/taskmanager/task/TaskRepository.kt
package com.example.taskmanager.task

import com.example.taskmanager.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Long> {
    fun findAllByUser(user: User): List<Task>
    fun findByIdAndUser(id: Long, user: User): Task?
}
