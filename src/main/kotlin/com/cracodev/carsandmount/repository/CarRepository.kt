package com.cracodev.carsandmount.repository

import com.cracodev.carsandmount.domain.Car
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<Car, Int> {
    fun findByColor(color: String, pageable: Pageable): Page<Car>
}