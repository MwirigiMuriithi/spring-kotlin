// src/main/kotlin/com/example/taskmanager/project/ProjectRepository.kt
package com.example.taskmanager.project

import com.example.taskmanager.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : JpaRepository<Project, Long> {
    fun findAllByUser(user: User): List<Project>
    fun findByIdAndUser(id: Long, user: User): Project?
}
