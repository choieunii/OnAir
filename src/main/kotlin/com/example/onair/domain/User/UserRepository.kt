package com.example.onair.domain.User

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface UserRepository: JpaRepository<User, Long> {
    fun findByUserId(user_id: String): User?
    fun findByEmail(email: String): User?

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.point = :point WHERE u.userId = :userId")
    fun updateUserPoint(point: Int, userId: String): Int?
}