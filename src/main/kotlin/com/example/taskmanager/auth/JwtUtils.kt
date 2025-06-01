// src/main/kotlin/com/example/taskmanager/auth/JwtUtils.kt
package com.example.taskmanager.auth

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import java.security.Key
import java.util.*
import org.springframework.stereotype.Component

@Component
class JwtUtils {
    private var jwtSecret: String = ""
    private var jwtExpirationMs: Long = 0

    private lateinit var key: Key

    @PostConstruct
    fun init() {
        jwtSecret = System.getenv("JWT_SECRET") ?: error("Missing JWT_SECRET env var")
        jwtExpirationMs = (System.getenv("JWT_EXPIRATION_MS") ?: "3600000").toLong()
        key = Keys.hmacShaKeyFor(jwtSecret.toByteArray(Charsets.UTF_8))
    }

    fun generateToken(username: String): String {
        val now = Date()
        val expiryDate = Date(now.time + jwtExpirationMs)
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact()
    }

    fun getUsernameFromJwt(token: String): String {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body.subject
    }

    fun validateJwtToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            true
        } catch (e: JwtException) {
            false
        }
    }
}
