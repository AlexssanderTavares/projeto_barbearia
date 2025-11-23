package com.project.barbearia.data.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity(name = "contato_cliente")
@Table(name = "contato_cliente")
data class ClientTel(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    val id: Int,

    @Column(name = "client")
    val clientId: Int,

    @Column(name = "telefone")
    val tel: String
)
