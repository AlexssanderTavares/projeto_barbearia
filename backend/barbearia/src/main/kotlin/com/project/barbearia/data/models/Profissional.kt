package com.project.barbearia.data.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity(name = "profissional")
@Table(name = "profissional")
data class Profissional(

    @Id
    val idProf: UUID,

    val idFilial: UUID,
    val name: String,
    val email: String,
    val tel: Tel
)