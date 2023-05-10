package com.cracodev.carsandmount.service

import com.cracodev.carsandmount.domain.Car
import com.cracodev.carsandmount.exception.BadRequestException
import com.cracodev.carsandmount.repository.CarRepository
import com.cracodev.carsandmount.requests.CarPostRequestBody
import com.cracodev.carsandmount.requests.CarPutRequestBody
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CarService {

    @Autowired
    private lateinit var carRepository: CarRepository
    fun listAll(pageable: Pageable): Page<Car> = carRepository.findAll(pageable)

    fun findAllByColorName(color: String, pageable: Pageable): Page<Car> {
        return carRepository.findByColor(color, pageable)
    }

    fun findByIdOrThrowBadRequest(id: Int): Car {
        println("Carro $id não encontrado!")
        return carRepository.findByIdOrNull(id) ?: throw BadRequestException("Carro $id não encontrado!")
    }

    fun save(carPostRequestBody: CarPostRequestBody): Car {
        val newCar = Car(carPostRequestBody)

        return carRepository.save(newCar)
    }

    fun edit(carPutRequestBody: CarPutRequestBody) {
        val newCar = Car(carPutRequestBody)

        carRepository.save(newCar)
    }

    fun delete(id: Int) = carRepository.deleteById(id)
}