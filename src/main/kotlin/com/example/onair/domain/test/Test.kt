package com.example.onair.domain.test

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "test")
data class Test(
        @Id
        @Column(name = "id")
        var id: String = "",
        @Column(name = "name")
        var name: String = "",
        @Column(name = "type")
        var type: String = "",
        @Column(name = "password")
        var password: String = "",
        @Column(name = "email")
        var email: String = ""
)