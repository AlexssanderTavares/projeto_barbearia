package com.example.projeto_barbearia.controllers.dtos.cliente.requests

import com.example.projeto_barbearia.utils.verifiers.EmailPatternVerifier
import com.example.projeto_barbearia.utils.verifiers.PasswordPatternVerifier
import com.example.projeto_barbearia.utils.verifiers.PatternVerifier

data class ClienteUpdateRequest(val newEmail: String?, val newPass: String?) {
    private val verifier: PatternVerifier

    init{
        when{

            newEmail != null -> {
                verifier = EmailPatternVerifier()
                require(verifier.verify(newEmail))
            }

            newPass != null -> {
                verifier = PasswordPatternVerifier()
                require(verifier.verify(newPass))
            }
            else -> throw IllegalArgumentException("Both data can't be null")
        }
    }
}