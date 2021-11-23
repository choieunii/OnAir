package com.example.onair.domain.airport

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "airport")
data class Airport(
        @Id
        @Column(name = "id", nullable=false)
        var id: Int,
        @Column(name = "name", nullable=false)
        var name: String,
        @Column(name = "location")
        var location: String,
)
