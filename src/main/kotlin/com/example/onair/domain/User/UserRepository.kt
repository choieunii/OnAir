package com.example.onair.domain.User

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByUserId(user_id: String): User?
    fun findByEmail(email: String): User?
}