package com.example.projeto_barbearia.utils.verifiers

interface PatternVerifier {

    val patterns: Set<Regex>

    fun verify(word: String) : Boolean
}