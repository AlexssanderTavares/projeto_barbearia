package com.example.projeto_barbearia.utils.verifiers

class BusinessCodeVerifier() : PatternVerifier {
    override val patterns: Set<Regex> = setOf(Regex("""(\d{2}).(\d{3}).(\d{3})/(\d{4})-(\d{2})"""))

    override fun verify(word: String): Boolean {
        return patterns.elementAt(0).matches(word)
    }
}