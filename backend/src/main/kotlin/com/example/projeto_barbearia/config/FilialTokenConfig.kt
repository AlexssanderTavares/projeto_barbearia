package com.example.projeto_barbearia.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.projeto_barbearia.data.models.Filial
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.Date

@Component
class FilialTokenConfig {

    private val secret: String = "secret"

    fun generateToken(filial: Filial) : String {
        val algor: Algorithm = Algorithm.HMAC256(secret)

        return JWT.create()
            .withClaim("id", filial.id?.toString())
            .withSubject(filial.email)
            .withExpiresAt(Date.from(Instant.now().plusSeconds(86400L)))
            .withIssuedAt(Instant.now())
            .sign(algor)
    }
}