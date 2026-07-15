package com.example.projeto_barbearia.controllers.dtos.cliente.requests

import java.util.UUID

data class ClienteGetRequest(val id: UUID?, val email: String?)
