package com.example.projeto_barbearia.services.utils.verifiers

class EmailPatternVerifier: PatternVerifier {

    override val patterns: Set<Regex> = setOf(Regex("[a-z0-9_.]+@[a-z0-9_.-]+\\.[a-z0-9_.]+"))

    override fun verify(code: String?) : Boolean{
        var res: Boolean =  false

        if(code != null) {
            res = patterns.elementAt(0).matches(code)
        }

        return res
    }
}