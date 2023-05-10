package com.cracodev.carsandmount.controller

import com.cracodev.carsandmount.domain.Car
import com.cracodev.carsandmount.requests.CarPostRequestBody
import com.cracodev.carsandmount.requests.CarPutRequestBody
import com.cracodev.carsandmount.service.CarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
class CarController {

    @Autowired
    private lateinit var carService: CarService

    @GetMapping()
    fun listAll(pageable: Pageable): ResponseEntity<Page<Car>> {
        return ResponseEntity.ok(carService.listAll(pageable))
    }

    @GetMapping("/find")
    fun findAllCarsByColorName(@RequestParam color: String, pageable: Pageable): ResponseEntity<Page<Car>> {
        return ResponseEntity.ok(carService.findAllByColorName(color, pageable));
    }

    @GetMapping("/find/{id}")
    fun findCarById(@PathVariable id: Int): ResponseEntity<Car> {
        return ResponseEntity.ok(carService.findByIdOrThrowBadRequest(id));
    }
    @PostMapping()
    fun save(@RequestBody carPostRequestBody: CarPostRequestBody): ResponseEntity<Car> {
        return ResponseEntity<Car>(carService.save(carPostRequestBody), HttpStatus.CREATED)
    }

    @PutMapping()
    fun edit(@RequestBody carPutRequestBody: CarPutRequestBody): ResponseEntity<Car> {
        carService.edit(carPutRequestBody);
        return ResponseEntity<Car>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Car> {
        carService.delete(id);
        return ResponseEntity<Car>(HttpStatus.NO_CONTENT);
    }
}