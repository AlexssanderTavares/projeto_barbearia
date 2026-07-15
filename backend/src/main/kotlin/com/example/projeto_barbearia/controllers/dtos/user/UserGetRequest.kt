package com.example.projeto_barbearia.controllers.dtos.user

import com.example.projeto_barbearia.utils.verifiers.EmailPatternVerifier
import com.example.projeto_barbearia.utils.verifiers.PatternVerifier

data class UserGetRequest(val userEmail: String) {

    init {
        var verifier: PatternVerifier= EmailPatternVerifier()
        require(verifier.verify(userEmail))
    }
}