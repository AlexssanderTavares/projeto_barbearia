package com.example.projeto_barbearia.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.projeto_barbearia.config.contracts.UserStrategy
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.Date

@Component
class TokenConfig {

    private val secret: String = "secret"

    fun generateToken(user: UserStrategy): String {
        val algor: Algorithm = Algorithm.HMAC256(secret)

        return JWT.create()
            .withClaim("id", user.id?.toString())
            .withSubject(user.email)
            .withExpiresAt(Date.from(Instant.now().plusSeconds(86400L)))
            .withIssuedAt(Instant.now())
            .sign(algor)
    }
}
