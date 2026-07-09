package com.example.projeto_barbearia.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.projeto_barbearia.data.models.Cliente
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.Date

@Component
class ClientTokenConfig {

    // secret must be comprehended and switched to the right string pattern
    private val secret: String = "secret"

    fun generateToken(user: Cliente) : String {
        val algor: Algorithm = Algorithm.HMAC256(secret)

        return JWT.create()
            .withClaim("id", user.id?.toString())
            .withSubject(user.email)
            .withExpiresAt(Date.from(Instant.now().plusSeconds(86400L)))
            .withIssuedAt(Instant.now())
            .sign(algor)
    }
}