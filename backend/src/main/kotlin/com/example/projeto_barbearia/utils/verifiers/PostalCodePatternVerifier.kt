package com.example.projeto_barbearia.utils.verifiers

class PostalCodePatternVerifier() : PatternVerifier {

    override val patterns: Set<Regex> = setOf(Regex("[\\d+\\-]"))

    override fun verify(word: String): Boolean {
        return patterns.elementAt(0).matches(word) && word.length == 9 || word.length == 8
    }
}