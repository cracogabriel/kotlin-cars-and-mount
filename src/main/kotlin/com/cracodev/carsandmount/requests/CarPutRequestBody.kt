package com.cracodev.carsandmount.requests

import jakarta.validation.constraints.NotEmpty

data class CarPutRequestBody(
        var id: Int,

        @NotEmpty(message = "The car name can't be empty")
        var name: String,

        @NotEmpty(message = "The car's color can't be empty")
        var color: String,

        var year: Int,
)
