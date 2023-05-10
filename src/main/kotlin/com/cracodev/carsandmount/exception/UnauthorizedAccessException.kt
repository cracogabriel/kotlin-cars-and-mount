package com.cracodev.carsandmount.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class UnauthorizedAccessException(override val message: String, val errors: List<String> = listOf(), val clientMessage: String = "Unauthorized access") : RuntimeException()
