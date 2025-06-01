// src/main/kotlin/com/example/taskmanager/user/UserMapper.kt
package com.example.taskmanager.user

import org.mapstruct.Mapper
import org.mapstruct.Mapping

// @Mapper(componentModel = "spring")
// interface UserMapper {
//     fun toDto(user: User): UserDTO         // Convert Entity to DTO
//     fun toEntity(userDTO: UserDTO): User   // Convert DTO to Entity
// }

@Mapper(componentModel = "spring")
interface UserMapper {
    @Mapping(target = "password", ignore = true)
    fun toEntity(userDto: UserDTO): User

    fun toDto(user: User): UserDTO
}

