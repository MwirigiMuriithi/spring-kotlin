// src/main/kotlin/com/example/taskmanager/user/User.kt
package com.example.taskmanager.user

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,           // Primary key
    var name: String = "",      // User's name
    var email: String = ""      // User's email
)
