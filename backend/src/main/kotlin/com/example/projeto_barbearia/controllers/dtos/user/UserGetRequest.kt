package com.example.projeto_barbearia.controllers.dtos.user

import com.example.projeto_barbearia.utils.verifiers.EmailPatternVerifier
import com.example.projeto_barbearia.utils.verifiers.PatternVerifier
import java.util.UUID

data class UserGetRequest(val id: UUID?, val email: String?, val pass: String)