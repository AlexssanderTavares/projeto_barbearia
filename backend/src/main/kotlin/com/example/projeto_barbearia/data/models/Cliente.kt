package com.project.barbearia.data.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity(name = "cliente")
@Table(name = "cliente")
data class Cliente(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_cliente")
    val id: UUID?,

    @Column(name = "nome")
    val name: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "pass")
    val pass: String,

    @Column(name = "endereco")
    val cep: String?
)
