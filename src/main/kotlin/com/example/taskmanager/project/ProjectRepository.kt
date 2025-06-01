// src/main/kotlin/com/example/taskmanager/project/ProjectRepository.kt
package com.example.taskmanager.project

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : JpaRepository<Project, Long>
