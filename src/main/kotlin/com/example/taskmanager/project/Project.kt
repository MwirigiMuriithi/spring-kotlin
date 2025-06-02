// src/main/kotlin/com/example/taskmanager/project/Project.kt
package com.example.taskmanager.project

import com.example.taskmanager.user.User
import jakarta.persistence.*

@Entity
@Table(name = "projects")
data class Project(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
        var name: String = "",
        var description: String = "",

        // Associate each Project with exactly one User (the “owner”)
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        var user: User? = null
        // var user: User
        // lateinit var user: User
)
// @Entity
// @Table(name = "projects")
// class Project {



//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     var id: Long = 0



//     var name: String = ""
//     var description: String = ""



//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "user_id", nullable = false)
//     lateinit var user: User



//     // No-args constructor (optional if using Kotlin JPA plugin)
//     constructor()



//     // Constructor for easier creation if you want
//     constructor(name: String, description: String, user: User) {
//         this.name = name
//         this.description = description
//         this.user = user
//     }
// }
