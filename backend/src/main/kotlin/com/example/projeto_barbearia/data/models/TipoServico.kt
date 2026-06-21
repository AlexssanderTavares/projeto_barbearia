package com.example.projeto_barbearia.data.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal


@Entity(name = "tiposervico")
@Table(name = "tiposervico")
data class TipoServico(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico")
    val id: Long,

    @Column(name = "nome")
    val name: String,

    @Column(name = "duracao")
    val duration: Int, /*** Duração em valor inteiro que corresponde aos minutos */

    @Column(name = "preco")
    var price: BigDecimal = BigDecimal.ZERO,

    @Column(name = "descricao")
    val description: String
)
