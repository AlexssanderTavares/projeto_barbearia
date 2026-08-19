package com.example.projeto_barbearia.config.contracts

import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

interface UserStrategy : UserDetails {

    var id: UUID?
    val name: String
    val nationalCertificate: String?
    val email: String
    val pass: String
}