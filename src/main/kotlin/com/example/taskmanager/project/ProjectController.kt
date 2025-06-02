// src/main/kotlin/com/example/taskmanager/project/ProjectController.kt
package com.example.taskmanager.project

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/projects")
class ProjectController(private val projectService: ProjectService) {

    // Get all projects
    @GetMapping
    fun getAllProjects(): List<ProjectDTO> = projectService.getAllProjects()

    // Get project by ID
    @GetMapping("/{id}")
    fun getProjectById(@PathVariable id: Long): ProjectDTO = projectService.getProjectById(id)

    // Create a new project
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProject(@RequestBody projectDTO: ProjectDTO): ProjectDTO =
        projectService.createProject(projectDTO)

    // Update an existing project
    @PutMapping("/{id}")
    fun updateProject(@PathVariable id: Long, @RequestBody projectDTO: ProjectDTO): ProjectDTO =
        projectService.updateProject(id, projectDTO)

    // Delete a project
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProject(@PathVariable id: Long) = projectService.deleteProject(id)
}
