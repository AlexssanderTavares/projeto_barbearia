package com.project.barbearia.data.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity(name = "endereco")
@Table(name = "endereco")
data class Endereco(

    @Id
    @Column(name = "cep")
    val cep: String,

    @Column(name = "estado")
    val estado: String,

    @Column(name = "bairro")
    val bairro: String
)
