package com.project.barbearia.data.models

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
    @GeneratedValue(strategy = GenerationType.UUID)
    val idProf: UUID,

    val idFilial: UUID,
    val name: String,
    val email: String,

)