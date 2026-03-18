package com.example.projeto_barbearia.services.utils

class PasswordPatternVerifier {

    private val patterns: Set<Regex> = setOf(Regex("[!@#$%&*/\\-]"), Regex("\\w+"), Regex("\\d+"))

    fun verify(password: String): Boolean{
        var isValid: Boolean = false
        var itContainsSymbols: Boolean = patterns.elementAt(0).containsMatchIn(password)
        var itIsAString: Boolean = patterns.elementAt(1).containsMatchIn(password)
        var itContainsADigitSequence: Boolean = patterns.elementAt(2).containsMatchIn(password)

        if(itContainsSymbols && itIsAString && itContainsADigitSequence){
            isValid = true
        }

        return isValid
    }
}