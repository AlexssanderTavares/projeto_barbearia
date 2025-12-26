package com.project.barbearia.data.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.UUID


@Entity(name = "ordemservico")
@Table(name = "ordemservico")
data class OrdemServico(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_os")
    val os: Long,

    @Column(name = "id_cliente")
    val idClient: UUID,

    @Column(name = "id_prof")
    val idProf: UUID,

    @Column(name = "agendamento")
    val date: LocalDateTime,

    @Column(name = "status")
    val status: String
)
