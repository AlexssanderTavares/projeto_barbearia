package com.project.barbearia.data.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity(name = "contato_profissional")
@Table(name = "contato_profissional")
data class ProfTel(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    val id: Int,

    @Column(name = "profissional")
    val profId: Int,

    @Column(name = "telefone")
    val tel: String
)
