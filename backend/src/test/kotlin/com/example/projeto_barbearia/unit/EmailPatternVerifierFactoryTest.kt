package com.example.projeto_barbearia.unit

import com.example.projeto_barbearia.services.utils.verifiers.EmailPatternVerifier
import org.junit.jupiter.api.Assertions.assertNotEquals
import kotlin.test.Test
import kotlin.test.assertEquals

class EmailPatternVerifierFactoryTest {

    private val verifier: EmailPatternVerifier = EmailPatternVerifier()

    @Test
    fun shouldVerifyStringsAndReturnTrue(){
        var str1: String = "dummy.test2000@test.com"
        var str2: String = "dummy_test0000@test.gov"
        var str3: String = "dummytest2134@test.com"

        var res1: Boolean = verifier.verify(str1)
        var res2: Boolean = verifier.verify(str2)
        var res3: Boolean = verifier.verify(str3)

        println(res1)
        println(res2)
        println(res3)

        assertEquals(true, res1)
        assertEquals(true, res2)
        assertEquals(true, res3)
    }

    @Test
    fun shouldVerifyStringsAndReturnFalse(){
        var str1: String = "dummy.test2000test.com"
        var str2: String = "dummy_test0000@testgov"

        var res1: Boolean = verifier.verify(str1)
        var res2: Boolean = verifier.verify(str2)

        println(res1)
        println(res2)

        assertNotEquals(true, res1)
        assertNotEquals(true, res2)
    }
}