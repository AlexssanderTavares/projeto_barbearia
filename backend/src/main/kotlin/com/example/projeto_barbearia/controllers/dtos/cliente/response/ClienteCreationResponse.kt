package com.example.projeto_barbearia.controllers.dtos.cliente.response

data class ClienteCreationResponse(val name: String, val email: String, val created: Boolean, val createdAt: String) {
}