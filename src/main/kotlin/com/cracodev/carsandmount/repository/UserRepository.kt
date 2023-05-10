package com.cracodev.carsandmount.repository

import com.cracodev.carsandmount.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
    fun findByUserName(username: String): User

}