package com.example.projeto_barbearia.controllers.dtos.cliente.response

import kotlin.time.Instant
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
data class ClienteUpdateResponse(val updated: Boolean, val updatedAt: String) {
}