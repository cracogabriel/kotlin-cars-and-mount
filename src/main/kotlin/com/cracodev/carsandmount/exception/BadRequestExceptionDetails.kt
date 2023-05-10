package com.cracodev.carsandmount.exception

import java.time.LocalDateTime


data class BadRequestExceptionDetails(
        val title: String,
        val status: Int,
        val details: String?,
        val developerMessage: String,
        val timestamp: LocalDateTime
)