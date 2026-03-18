package com.example.projeto_barbearia.unit

import com.project.barbearia.services.utils.UniqueDataChecker
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Import
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class UniqueDataCheckerTest {

    private lateinit var checker: UniqueDataChecker

    @BeforeTest
    fun setup() {
        checker = UniqueDataChecker()
    }

    @Test
    fun shouldVerifyAnEmailPatternAndSucceed() {
        val email: String = "dummy@test.com"

        val res: Boolean = checker.verifyEmail(email)
        println("Returned: $res")
        assertEquals(true, res)
    }

    @Test
    fun shouldVerifyAnEmailAndReturnFalse() {
        val email1: String = "dummytest.com"
        val email2: String = "dummy@testcom"

        val res1: Boolean = checker.verifyEmail(email1)
        val res2: Boolean = checker.verifyEmail(email2)

        println("Returned: $res1")
        println("Returned: $res2")

        assertNotEquals(true, res1)
        assertNotEquals(true, res2)
    }

}