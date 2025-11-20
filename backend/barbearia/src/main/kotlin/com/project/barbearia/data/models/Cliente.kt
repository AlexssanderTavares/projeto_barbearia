package com.project.barbearia.data.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity(name = "cliente")
@Table(name = "cliente")
data class Cliente(

    @Id
    val id: UUID,
    val name: String,
    val email: String,
    val pass: String,
    val tel: ArrayList<String?>,
    val cep: String
)
