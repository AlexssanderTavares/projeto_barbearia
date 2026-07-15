package com.example.projeto_barbearia.utils.data_attributes

import com.example.projeto_barbearia.utils.verifiers.PasswordPatternVerifier

class EntityPass(pass: String) {

    private val verifier: PasswordPatternVerifier = PasswordPatternVerifier()

    init{
        require(verifier.verify(pass))
    }
}