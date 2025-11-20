package com.project.barbearia.data.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID


@Entity(name = "filial")
@Table(name = "filial")
data class Filial(

    @Id
    val id: UUID,
    val cnpj: String,
    val name: String,
    val email: String,
    val pass: String,
    var qt_prof: Int = 0,
    val tel: ArrayList<String?>,
    val cep: String
    )
