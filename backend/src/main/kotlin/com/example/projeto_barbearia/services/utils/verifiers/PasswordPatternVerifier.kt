package com.example.projeto_barbearia.services.utils.verifiers

class PasswordPatternVerifier: PatternVerifier {

    override val patterns: Set<Regex> = setOf(Regex("[!@#$%&*/\\-]"), Regex("\\w+"), Regex("\\d+"))

    override fun verify(code: String?): Boolean{
        var isValid: Boolean = false
        if(code != null) {
            var itContainsSymbols: Boolean = patterns.elementAt(0).containsMatchIn(code)
            var itIsAString: Boolean = patterns.elementAt(1).containsMatchIn(code)
            var itContainsADigitSequence: Boolean = patterns.elementAt(2).containsMatchIn(code)

            if (itContainsSymbols && itIsAString && itContainsADigitSequence) {
                isValid = true
            }

        }

        return isValid
    }
}