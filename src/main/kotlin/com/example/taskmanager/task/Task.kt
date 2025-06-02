// src/main/kotlin/com/example/taskmanager/task/Task.kt
package com.example.taskmanager.task

import com.example.taskmanager.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tasks")
data class Task(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var title: String = "",
    var description: String = "",

    @Enumerated(EnumType.STRING)
    var status: TaskStatus = TaskStatus.TODO,

    // Associate each Task with exactly one User (the “owner”)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null
)

enum class TaskStatus {
    TODO,
    IN_PROGRESS,
    DONE
}
