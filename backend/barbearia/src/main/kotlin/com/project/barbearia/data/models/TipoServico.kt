package com.project.barbearia.data.models

import jakarta.persistence.Column
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
    @Column(name = "id_servico")
    val id: Int,

    @Column(name = "nome")
    val name: String,

    @Column(name = "duracao")
    val duration: Int, /*** Duração em valor inteiro que corresponde aos minutos */

    @Column(name = "preco")
    val price: BigDecimal,

    @Column(name = "qt_servico")
    val quantity: Int
)
