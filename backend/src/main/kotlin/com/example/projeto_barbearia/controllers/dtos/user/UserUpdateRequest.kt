package com.example.projeto_barbearia.controllers.dtos.user

import com.example.projeto_barbearia.utils.verifiers.EmailPatternVerifier
import com.example.projeto_barbearia.utils.verifiers.PasswordPatternVerifier
import com.example.projeto_barbearia.utils.verifiers.PatternVerifier

data class UserUpdateRequest(val newEmail: String?, val newPass: String?) {

    private lateinit var verifier: PatternVerifier

    init {
        if (newEmail != null) {
            verifier = EmailPatternVerifier()
            require(verifier.verify(newEmail))
        }

        if (newPass != null) {
            verifier = PasswordPatternVerifier()
            require(verifier.verify(newPass))
        }

    }
}
