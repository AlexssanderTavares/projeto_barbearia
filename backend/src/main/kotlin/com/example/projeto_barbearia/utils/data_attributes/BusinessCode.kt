package com.example.projeto_barbearia.utils.data_attributes

import com.example.projeto_barbearia.utils.verifiers.BusinessCodeVerifier

class BusinessCode(code: String) {

    private val verifier: BusinessCodeVerifier = BusinessCodeVerifier()

    init{
        require(verifier.verify(code))
    }
}