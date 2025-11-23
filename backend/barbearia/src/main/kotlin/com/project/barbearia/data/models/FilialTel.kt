package com.project.barbearia.data.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity(name = "contato_filial")
@Table(name = "contato_filial")
data class FilialTel(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    val id: Int,

    @Column(name = "filial")
    val filialId: UUID,

    @Column(name = "telefone")
    val tel: String
)
