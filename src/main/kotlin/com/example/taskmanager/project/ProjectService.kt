// src/main/kotlin/com/example/taskmanager/project/ProjectService.kt
package com.example.taskmanager.project

import com.example.taskmanager.user.UserRepository
import com.example.taskmanager.user.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val projectMapper: ProjectMapper,
    private val userRepository: UserRepository    // ⬅️ Inject UserRepository
) {

    // Helper: get currently authenticated User
    private fun getCurrentUser(): User {
        val username = SecurityContextHolder.getContext().authentication.name
        println("Authenticated username: $username")
        return userRepository.findByUsername(username)
            ?: throw IllegalStateException("User not found: $username")
    }

    // List all projects for current user
    fun getAllProjects(): List<ProjectDTO> {
        val user = getCurrentUser()
        return projectRepository.findAllByUser(user)
            .map { projectMapper.toDto(it) }
    }

    // Get a project by ID only if it belongs to current user
    fun getProjectById(id: Long): ProjectDTO {
        val user = getCurrentUser()
        val project = projectRepository.findByIdAndUser(id, user)
            ?: throw NoSuchElementException("Project not found or not owned by user: $id")
        return projectMapper.toDto(project)
    }

    // Create a new project under current user
    fun createProject(projectDTO: ProjectDTO): ProjectDTO {
        val user = getCurrentUser()
        val project = projectMapper.toEntity(projectDTO).apply { this.user = user }
        return projectMapper.toDto(projectRepository.save(project))
    }

    // Update a project only if owned by current user
    fun updateProject(id: Long, projectDTO: ProjectDTO): ProjectDTO {
        val user = getCurrentUser()
        val existingProject = projectRepository.findByIdAndUser(id, user)
            ?: throw NoSuchElementException("Project not found or not owned by user: $id")
        val projectToUpdate = projectMapper.toEntity(projectDTO).apply {
            this.id = id
            this.user = user
        }
        return projectMapper.toDto(projectRepository.save(projectToUpdate))
    }

    // Delete a project only if owned by current user
    fun deleteProject(id: Long) {
        val user = getCurrentUser()
        val project = projectRepository.findByIdAndUser(id, user)
            ?: throw NoSuchElementException("Project not found or not owned by user: $id")
        projectRepository.delete(project)
    }
}
