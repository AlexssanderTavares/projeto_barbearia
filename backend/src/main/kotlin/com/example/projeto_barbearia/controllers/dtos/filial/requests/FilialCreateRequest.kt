package com.example.projeto_barbearia.controllers.dtos.filial.requests

data class FilialCreateRequest(val name: String, val email: String, val pass: String, val businessCode: String) {
}