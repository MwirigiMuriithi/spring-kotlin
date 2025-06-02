package com.example.taskmanager.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.security.core.userdetails.UserDetails

@Component
class JwtAuthenticationFilter(
    private val jwtUtils: JwtUtils,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val header = request.getHeader("Authorization")
            log.info("Authorization header: $header")

            if (header != null && header.startsWith("Bearer ")) {
                val token = header.substring(7)
                log.info("Token extracted: $token")

                if (jwtUtils.validateJwtToken(token)) {
                    log.info("JWT token is valid")
                    val username = jwtUtils.getUsernameFromJwt(token)
                    log.info("Username from token: $username")

                    val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
                    log.info("UserDetails loaded: $userDetails")

                    val authentication = UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.authorities
                    )
                    SecurityContextHolder.getContext().authentication = authentication
                    log.info("Security context updated with authentication")
                } else {
                    log.warn("JWT token validation failed")
                }
            } else {
                log.info("No Bearer token found in Authorization header")
            }
        } catch (ex: Exception) {
            log.error("Cannot set user authentication", ex)
        }

        filterChain.doFilter(request, response)
    }
}
