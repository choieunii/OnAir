package com.example.onair.domain.AirportCode

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "airportCode")
data class AirportCode(
    @Id
    @Column(name = "ServiceKey")
    var ServiceKey: String = "yte0lqvUBjmStje3Bv6YEA5dectrmAum%2BiBn%2FCK3vg3OZo1NTSaI%2BFpfJYfuA5%2FO3Q6VXBMMMUlmAbCZnIaBVA%3D%3D"
)