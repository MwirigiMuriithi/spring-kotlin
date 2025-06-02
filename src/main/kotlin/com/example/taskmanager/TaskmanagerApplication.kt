//src/main/kotlin/com/example/taskmanager/TaskmanagerApplication.kt
package com.example.taskmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import io.github.cdimascio.dotenv.dotenv

@SpringBootApplication
class TaskmanagerApplication

fun main(args: Array<String>) {
    // Load the .env file into environment variables
    val dotenv = dotenv {
        ignoreIfMissing = true // allows running in prod without .env
    }

    dotenv.entries().forEach {
        System.setProperty(it.key, it.value)
    }

    runApplication<TaskmanagerApplication>(*args)
}
