package com.example.projeto_barbearia.data.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "contato_cliente")
data class ClientTel(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contato_cliente_id_seq")
    @SequenceGenerator(name = "contato_cliente_id_seq", sequenceName = "contato_cliente_id_seq", allocationSize = 1)
    @Column(name = "id")
    val id: Int,

    @Column(name = "client")
    val clientId: UUID,

    @Column(name = "telefone")
    val tel: String
)
