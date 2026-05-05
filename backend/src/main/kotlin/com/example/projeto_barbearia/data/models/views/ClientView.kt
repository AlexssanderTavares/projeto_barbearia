package com.project.barbearia.data.models.views

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable
import java.util.UUID

@Immutable
@Entity
@Table(name = "cliente_view")
data class ClientView(

    @Id
    @Column(name = "id_cliente")
    val id: UUID,

    @Column(name = "nome")
    val name: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "endereco")
    val endereco: String,

    @Column(name = "telefone")
    val contato: String,
)

