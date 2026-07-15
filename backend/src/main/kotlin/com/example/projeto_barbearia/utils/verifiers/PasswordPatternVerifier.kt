package com.example.projeto_barbearia.utils.verifiers

class PasswordPatternVerifier(): PatternVerifier {

    override val patterns: Set<Regex> = setOf(Regex("[!@#$%&*/\\-]"), Regex("\\w+"), Regex("\\d+"))

    override fun verify(word: String): Boolean{
        return patterns.elementAt(0).containsMatchIn(word) &&
                patterns.elementAt(1).containsMatchIn(word) &&
                patterns.elementAt(2).containsMatchIn(word)
    }
}