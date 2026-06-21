package com.example.projeto_barbearia.services.utils.data_attributes

import com.example.projeto_barbearia.services.utils.verifiers.EmailPatternVerifier

class EntityEmail(email: String) {

    private val verifier: EmailPatternVerifier = EmailPatternVerifier()
    init{
        require(verifier.verify(email))
    }
}