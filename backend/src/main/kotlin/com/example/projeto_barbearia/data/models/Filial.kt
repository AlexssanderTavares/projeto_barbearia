package com.example.projeto_barbearia.data.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID


@Entity(name = "filial")
@Table(name = "filial")
data class Filial(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_filial")
    val id: UUID? = null,

    @Column(name = "cnpj")
    val cnpj: String,

    @Column(name = "nome")
    val name: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "pass")
    val pass: String,

    @Column(name = "qtd_profissionais")
    val qt_prof: Int,
    )
