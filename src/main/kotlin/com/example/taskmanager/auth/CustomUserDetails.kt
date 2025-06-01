// src/main/kotlin/com/example/taskmanager/auth/CustomUserDetails.kt
package com.example.taskmanager.auth

import com.example.taskmanager.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

// Wrap our User entity as Spring Security’s UserDetails
class CustomUserDetails(private val user: User) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> =
        listOf(SimpleGrantedAuthority(user.role))

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.username

    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}
