// src/main/kotlin/com/example/taskmanager/user/UserRepository.kt
package com.example.taskmanager.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

// @Repository
// interface UserRepository : JpaRepository<User, Long>

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String): Boolean
}
