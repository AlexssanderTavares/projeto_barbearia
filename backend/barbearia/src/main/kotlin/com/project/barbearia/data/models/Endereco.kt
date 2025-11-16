package com.project.barbearia.data.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity(name = "endereco")
@Table(name = "endereco")
data class Endereco(

    @Id
    val cep: String,
    val estado: String,
    val bairro: String
)
