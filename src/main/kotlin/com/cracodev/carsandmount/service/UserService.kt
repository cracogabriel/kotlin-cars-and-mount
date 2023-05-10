package com.cracodev.carsandmount.service

import com.cracodev.carsandmount.domain.User
import com.cracodev.carsandmount.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByUserName(username ?: throw UsernameNotFoundException("User not found with username: $username"))

        return User(
                user.id,
                user.userName,
                user.userEmail,
                user.userPassword
        )
    }

}