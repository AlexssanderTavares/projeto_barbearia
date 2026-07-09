package com.example.projeto_barbearia.unit

import com.example.projeto_barbearia.services.utils.verifiers.PasswordPatternVerifier
import org.junit.jupiter.api.Assertions.assertNotEquals
import kotlin.test.Test
import kotlin.test.assertEquals

class PasswordPatternVerifierFactoryTest {

    private val verifier: PasswordPatternVerifier = PasswordPatternVerifier()

    @Test
    fun verifyStringAndCheckIfHaveSymbolAndSucceed(){
        val str1: String = "@Dummy456"
        val str2: String = "Dummy@#%456"
        val str3: String = "Dum%*&my456"

        println(verifier.verify(str1))
        println(verifier.verify(str2))
        println(verifier.verify(str3))

        assertEquals(true, verifier.verify(str1))
        assertEquals(true, verifier.verify(str2))
        assertEquals(true, verifier.verify(str3))
    }

    @Test
    fun verifyStringAndCheckIfHaveSymbolAndFail(){
        val str1: String = "Dummy456"
        val str2: String = "Dum456my"
        val str3: String = "456Dummy"

        println(verifier.verify(str1))
        println(verifier.verify(str2))
        println(verifier.verify(str3))

        assertNotEquals(true, verifier.verify(str1))
        assertNotEquals(true, verifier.verify(str2))
        assertNotEquals(true, verifier.verify(str3))
    }
}