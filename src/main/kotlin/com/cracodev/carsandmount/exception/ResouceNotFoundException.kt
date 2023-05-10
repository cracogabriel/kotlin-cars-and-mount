package com.cracodev.carsandmount.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException(override val message: String, val errors: List<String> = listOf(), val clientMessage: String = "Resource not found") : RuntimeException()
