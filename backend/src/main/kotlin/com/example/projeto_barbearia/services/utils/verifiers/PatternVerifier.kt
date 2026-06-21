package com.example.projeto_barbearia.services.utils.verifiers

interface PatternVerifier {

    val patterns: Set<Regex>

    fun verify(code: String?) : Boolean
}