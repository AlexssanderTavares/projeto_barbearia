package com.example.projeto_barbearia.data.dtos.cliente

import java.util.UUID

data class ClienteView(val id: UUID, val name: String, val email: String)