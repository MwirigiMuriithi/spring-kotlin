package com.example.taskmanager.common

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PublicController {

    @GetMapping("/api/public/hello")
    fun hello(): String = "Hello from public API"
}
