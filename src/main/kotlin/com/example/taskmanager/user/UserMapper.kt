// src/main/kotlin/com/example/taskmanager/user/UserMapper.kt
package com.example.taskmanager.user

import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UserMapper {
    fun toDto(user: User): UserDTO         // Convert Entity to DTO
    fun toEntity(userDTO: UserDTO): User   // Convert DTO to Entity
}
