// src/main/kotlin/com/example/taskmanager/task/TaskMapper.kt
package com.example.taskmanager.task

import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface TaskMapper {
    fun toDto(task: Task): TaskDTO      // Entity to DTO
    fun toEntity(taskDTO: TaskDTO): Task // DTO to Entity
}
