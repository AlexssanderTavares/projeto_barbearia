package com.example.projeto_barbearia.config.contracts

import java.util.UUID

interface UserStrategy {

    val id: UUID?
    val name: String
    val email: String
    val pass: String
}