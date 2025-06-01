// src/main/kotlin/com/example/taskmanager/user/User.kt
package com.example.taskmanager.user

import jakarta.persistence.*

// @Entity
// @Table(name = "users")
// data class User(
//     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//     var id: Long = 0,           // Primary key
//     var name: String = "",      // User's name
//     var email: String = ""      // User's email
// )



@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(unique = true, nullable = false)
    var username: String = "",   // used for login

    @Column(unique = true, nullable = false)
    var email: String = "",      // user's email

    @Column(nullable = false)
    var password: String = "",   // hashed password

    var role: String = "ROLE_USER"  
)
