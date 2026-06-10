package com.example.projeto_barbearia.data.models.views

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable
import java.util.UUID

@Entity
@Table(name = "cliente_view")
@Immutable
data class ClienteView(

    @Id
    @Column(name = "id_cliente")
    var id_cliente: UUID,

    @Column(name = "nome")
    val nome: String,

    @Column(name = "email")
    val email: String
)

