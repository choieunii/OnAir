package com.example.onair.domain.User

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<User, Long> {
    fun findByUserId(user_id: String): User?
    fun findByEmail(email: String): User?

    @Query("UPDATE User u SET u.point = :newValue WHERE u.userId = :userId")
    fun setBalance(newValue : Int, userId : String)
}
