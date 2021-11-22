package com.example.onair.domain.book

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<Book, Long> {
    @Query("SELECT MAX(BookId) FROM Book")
    fun getMaxId() : Long
}
