package com.cracodev.carsandmount.controller

import com.cracodev.carsandmount.requests.UserPostRequestBody
import com.cracodev.carsandmount.requests.UserResponseBody
import com.cracodev.carsandmount.service.AuthService
import com.cracodev.carsandmount.service.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    private lateinit var userService: UserDetailsServiceImpl

    @Autowired
    private lateinit var authService: AuthService

    @PostMapping("/sign-in")
    fun authenticateUser(@RequestBody userPostRequestBody: UserPostRequestBody): ResponseEntity<UserResponseBody> {
        val username = userPostRequestBody.username
        println("sing-in with user $username")

        val token = authService.auth(userPostRequestBody)

        println(token)

        return ResponseEntity.ok(token)
    }
}