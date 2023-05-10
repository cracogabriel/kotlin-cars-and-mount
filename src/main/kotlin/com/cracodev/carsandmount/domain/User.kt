package com.cracodev.carsandmount.domain

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "user")
data class User(
        @Id
        @Column(name = "USER_ID")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,

        @Column(name = "USER_NAME")
        val userName: String,

        @Column(name = "USER_EMAIL")
        val userEmail: String?,

        @Column(name = "USER_PASSWORD")
        val userPassword: String?,

) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority("ROLE_ADMIN"))
    }

    override fun getPassword(): String? {
        return userPassword
    }

    override fun getUsername(): String? {
        return userName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}

