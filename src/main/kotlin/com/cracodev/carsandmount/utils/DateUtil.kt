package com.cracodev.carsandmount.utils

import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class DateUtil {
    fun formatLocalDateTimeToDatabaseStyle(localDateTime: LocalDateTime): String {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime)
    }
}