package com.cracodev.carsandmount.configuration

import com.cracodev.carsandmount.service.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
                .authorizeHttpRequests(Customizer { authz ->
                    authz
                            .requestMatchers("/auth/**").permitAll()
                            .requestMatchers("/cars/**").hasRole("ADMIN")
                            .anyRequest().denyAll()
                }).httpBasic(withDefaults())
        return http.csrf().disable().build()
    }


    @Autowired
    private lateinit var userDetailsService: UserDetailsServiceImpl

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun daoAuthenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }

    @Bean
    @Throws(java.lang.Exception::class)
    fun authenticationManager(http: HttpSecurity): AuthenticationManager? {
        return http.getSharedObject(AuthenticationManagerBuilder::class.java).build()
    }

}
