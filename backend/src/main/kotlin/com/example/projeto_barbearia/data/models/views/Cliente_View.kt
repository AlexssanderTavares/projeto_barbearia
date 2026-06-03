package com.example.projeto_barbearia.data.models.views

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Generated
import org.hibernate.annotations.Immutable
import org.hibernate.annotations.Subselect
import org.hibernate.annotations.Synchronize
import org.hibernate.annotations.UuidGenerator
import java.util.UUID

@Entity
@Table(name = "cliente_view")
@Immutable
data class Cliente_View(

    @Id
    @Column(name = "id_cliente")
    var id_cliente: UUID,

    @Column(name = "nome")
    val nome: String,

    @Column(name = "email")
    val email: String
)

