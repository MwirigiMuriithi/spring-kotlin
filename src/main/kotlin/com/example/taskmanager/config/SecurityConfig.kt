//src/main/kotlin/com/example/taskmanager/config/SecurityConfig.kt
package com.example.taskmanager.config


import com.example.taskmanager.auth.CustomUserDetailsService
import com.example.taskmanager.auth.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter



// import org.springframework.context.annotation.Bean
// import org.springframework.context.annotation.Configuration
// import org.springframework.security.config.annotation.web.builders.HttpSecurity
// import org.springframework.security.config.http.SessionCreationPolicy
// import org.springframework.security.web.SecurityFilterChain

// @Configuration
// class SecurityConfig {

//     @Bean
//     fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//         http
//             .csrf { it.disable() }
//             .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
//             .authorizeHttpRequests {
//                 it.requestMatchers("/api/public/**").permitAll()
//                 it.anyRequest().authenticated()
//             }
//             .formLogin { it.disable() }
//             .httpBasic { it.disable() }

//         return http.build()
//     }
// }

@Configuration
@EnableMethodSecurity
class SecurityConfig(
    private val userDetailsService: CustomUserDetailsService,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager =
        authConfig.authenticationManager

    @Bean
    fun daoAuthProvider(): DaoAuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(userDetailsService)
        provider.setPasswordEncoder(passwordEncoder())
        return provider
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/api/auth/**").permitAll() // login/register
                    .requestMatchers("/h2-console/**").permitAll() // dev DB console
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // swagger (if used)
                    .anyRequest().authenticated()
            }
            .headers { it.frameOptions { frame -> frame.sameOrigin() } } // for H2 console frames
            .authenticationProvider(daoAuthProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}
