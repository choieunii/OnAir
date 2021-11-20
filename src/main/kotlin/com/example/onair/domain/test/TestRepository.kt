package com.example.onair.domain.test

import org.aspectj.weaver.ast.Test
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TestRepository : JpaRepository<Test, String>