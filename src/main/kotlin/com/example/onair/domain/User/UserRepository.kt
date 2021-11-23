package com.example.onair.domain.User

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository: JpaRepository<User, Long> {
    fun findByUserId(user_id: String): User?
    fun findByEmail(email: String): User?

    @Query("UPDATE USER SET balance = :newValue WHERE user_id = :userId")
    fun setBalance(@Param("newValue")newValue : Long, @Param("user_id")userId : String)

    @Query("SELECT balance FROM USER WHERE user_id = :user_id")
    fun getBalance(@Param("user_id")user_id : String) : Long
}
