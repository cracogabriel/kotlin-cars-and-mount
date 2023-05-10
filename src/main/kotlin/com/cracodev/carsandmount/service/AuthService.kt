package com.cracodev.carsandmount.service

import com.cracodev.carsandmount.exception.UnauthorizedAccessException
import com.cracodev.carsandmount.requests.UserPostRequestBody
import com.cracodev.carsandmount.requests.UserResponseBody
import com.cracodev.carsandmount.utils.AuthUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class AuthService {
    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var userService: UserDetailsServiceImpl

    @Autowired
    lateinit var authUtils: AuthUtils

    private fun authenticationWithManager(username: String, password: String): Authentication {
        val userDetails = userService.loadUserByUsername(username)


        return UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.password,
                userDetails.authorities
        )
    }

    fun verifyAuthorization(userDetails: UserDetails) {
        if (!userDetails.isAccountNonLocked) throw UnauthorizedAccessException("The ${userDetails.username} password has been blocked")
        if (!userDetails.isAccountNonExpired) throw UnauthorizedAccessException("The ${userDetails.username} account has been blocked")
    }

    fun auth(userPostRequestBody: UserPostRequestBody): UserResponseBody {
        val username = userPostRequestBody.username
        val password = userPostRequestBody.password

        val authentication = authenticationWithManager(username, password)

        val userDetails = loadUserDetails(username)
        verifyAuthorization(userDetails)

        val token = authUtils.generateJwtToken(authentication)

        return UserResponseBody(username, token)
    }

    fun loadUserDetails(login: String): UserDetails {
        return userService.loadUserByUsername(login)
    }

}