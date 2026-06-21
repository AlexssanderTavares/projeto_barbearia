package com.example.projeto_barbearia.data.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "cliente")
data class Cliente(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_cliente")
    var id: UUID? = null,

    @Column(name = "nome")
    val name: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "pass")
    val pass: String,
    )