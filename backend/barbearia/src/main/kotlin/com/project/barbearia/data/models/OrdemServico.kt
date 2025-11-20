package com.project.barbearia.data.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Timestamp
import java.util.UUID


@Entity(name = "ordemservico")
@Table(name = "ordemservico")
data class OrdemServico(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val os: Int,
    val idClient: UUID,
    val idProf: UUID,
    val date: Timestamp
)
