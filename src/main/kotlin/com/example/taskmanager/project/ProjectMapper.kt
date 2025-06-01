// src/main/kotlin/com/example/taskmanager/project/ProjectMapper.kt
package com.example.taskmanager.project

import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ProjectMapper {
    fun toDto(project: Project): ProjectDTO      // Entity to DTO
    fun toEntity(projectDTO: ProjectDTO): Project // DTO to Entity
}
