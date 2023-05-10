package com.cracodev.carsandmount.handler

import com.cracodev.carsandmount.exception.BadRequestExceptionDetails
import com.cracodev.carsandmount.exception.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class RestReceptionHandler {
    @ExceptionHandler(BadRequestException::class)
    fun handlerBadRequestException(badRequestException: BadRequestException): ResponseEntity<BadRequestExceptionDetails> {
        val newBadRequestException = BadRequestExceptionDetails(
                "Bad Request Exceptions",
                HttpStatus.BAD_REQUEST.value(),
                badRequestException.message,
                badRequestException.javaClass.name,
                LocalDateTime.now())
        return ResponseEntity<BadRequestExceptionDetails>(newBadRequestException, HttpStatus.BAD_REQUEST)
    }
}