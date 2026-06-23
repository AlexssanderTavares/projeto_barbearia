package com.example.projeto_barbearia.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.projeto_barbearia.config.contracts.UserStrategy
import java.time.Instant
import java.util.Date

class TokenConfig {

    // secret must be comprehended and switched to the right string pattern
    private val secret: String = ""

    fun generateToken(user: UserStrategy) : String {
        val algor: Algorithm = Algorithm.HMAC256(secret)

        return JWT.create()
            .withClaim("id", user.id?.toString())
            .withSubject(user.email)
            .withExpiresAt(Date.from(Instant.now().plusSeconds(86400L)))
            .withIssuedAt(Instant.now())
            .sign(algor)
    }
}