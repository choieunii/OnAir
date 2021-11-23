package com.example.onair.domain.User

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<User, Long> {
    fun findByUserId(user_id: String): User?
    fun findByEmail(email: String): User?
//    @Query("UPDATE USER SET balance = %?1% WHERE user_id = %?2%")
//    fun setBalance(newValue : Long, userId : String)
//    @Query("SELECT balance FROM USER WHERE user_id = %?1%")
//    fun getBalance(user_id : String) : Long
}
