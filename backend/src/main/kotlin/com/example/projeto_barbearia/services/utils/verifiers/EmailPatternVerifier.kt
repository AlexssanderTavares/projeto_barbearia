package com.example.projeto_barbearia.services.utils.verifiers

class EmailPatternVerifier: PatternVerifier {

    override val patterns: Set<Regex> = setOf(Regex("[a-z0-9_.]+@[a-z0-9_.-]+\\.[a-z0-9_.]+"))

    override fun verify(code: String?) : Boolean{
        return patterns.elementAt(0).matches(code!!)
    }
}