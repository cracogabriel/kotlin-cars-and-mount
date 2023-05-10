package com.cracodev.carsandmount.domain

import com.cracodev.carsandmount.requests.CarPostRequestBody
import com.cracodev.carsandmount.requests.CarPutRequestBody
import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty

@Entity
@Table(name = "car")
data class Car(
        @Id
        @Column(name = "CAR_ID")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column(name = "CAR_NAME")
        var name: String,

        @Column(name = "CAR_COLOR")
        var color: String,

        @Column(name = "CAR_YEAR")
        var year: Int,
) {
    constructor(carPutRequestBody: CarPutRequestBody) : this(
            id = carPutRequestBody.id,
            name = carPutRequestBody.name,
            color = carPutRequestBody.color,
            year = carPutRequestBody.year,
    )

    constructor(carPostRequestBody: CarPostRequestBody) : this(
            name = carPostRequestBody.name,
            color = carPostRequestBody.color,
            year = carPostRequestBody.year,
    )
}
