package com.example.projeto_barbearia.data.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity(name = "profissional")
@Table(name = "profissional")
data class Profissional(

    @Id
    @Column(name = "id_prof")
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,

    @Column(name = "id_filial")
    val idFilial: UUID,

    @Column(name = "nome")
    val name: String,

    @Column(name = "email")
    val email: String,

)