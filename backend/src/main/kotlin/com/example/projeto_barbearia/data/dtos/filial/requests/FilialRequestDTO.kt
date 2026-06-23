package com.example.projeto_barbearia.data.dtos.filial.requests

data class FilialRequestDTO(val cnpj: String, val name: String, val email: String, val pass: String?, val qtProf: Int)