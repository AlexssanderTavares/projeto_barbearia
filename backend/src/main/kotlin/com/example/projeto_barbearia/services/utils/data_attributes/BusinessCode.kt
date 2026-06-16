package com.example.projeto_barbearia.services.utils.data_attributes

import com.example.projeto_barbearia.services.utils.verifiers.BusinessCodeVerifier

class BusinessCode(code: String) {

    private val verifier: BusinessCodeVerifier = BusinessCodeVerifier()

    init{
        require(verifier.verify(code))
    }
}