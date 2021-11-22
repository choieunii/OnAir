package com.example.onair.service
import com.example.onair.domain.test.Test
import com.example.onair.domain.test.TestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TestService (private val testRepository: TestRepository){
    fun getTasks(): List<Test> =
            testRepository.findAll()
}