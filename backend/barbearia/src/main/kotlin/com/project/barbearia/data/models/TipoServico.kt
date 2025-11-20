package com.project.barbearia.data.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID


@Entity(name = "tiposervico")
@Table(name = "tiposervico")
data class TipoServico(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    val duration: Int, /*** Duração em valor inteiro que corresponde aos minutos */
    val price: BigDecimal,
    val quantity: Int,
    val client: UUID
)
