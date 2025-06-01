// src/main/kotlin/com/example/taskmanager/project/ProjectService.kt
package com.example.taskmanager.project

import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val projectMapper: ProjectMapper
) {
    // Retrieve all projects
    fun getAllProjects(): List<ProjectDTO> =
        projectRepository.findAll().map { projectMapper.toDto(it) }

    // Retrieve a single project by ID
    fun getProjectById(id: Long): ProjectDTO =
        projectRepository.findById(id)
            .map { projectMapper.toDto(it) }
            .orElseThrow { NoSuchElementException("Project not found with id: $id") }

    // Create a new project
    fun createProject(projectDTO: ProjectDTO): ProjectDTO {
        val project = projectMapper.toEntity(projectDTO)
        return projectMapper.toDto(projectRepository.save(project))
    }

    // Update an existing project
    fun updateProject(id: Long, projectDTO: ProjectDTO): ProjectDTO {
        if (!projectRepository.existsById(id)) {
            throw NoSuchElementException("Project not found with id: $id")
        }
        val projectToUpdate = projectMapper.toEntity(projectDTO).apply { this.id = id }
        return projectMapper.toDto(projectRepository.save(projectToUpdate))
    }

    // Delete a project by ID
    fun deleteProject(id: Long) {
        if (!projectRepository.existsById(id)) {
            throw NoSuchElementException("Project not found with id: $id")
        }
        projectRepository.deleteById(id)
    }
}
