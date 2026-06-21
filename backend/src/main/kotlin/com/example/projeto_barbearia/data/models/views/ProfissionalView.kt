package com.example.projeto_barbearia.data.models.views

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable
import org.hibernate.annotations.View
import java.util.UUID


@Entity
@Table(name = "profissional_view")
@Immutable
data class ProfissionalView(

    @Id
    @Column(name = "id_prof")
    val id: UUID,

    @Column(name = "id_filial")
    val idFilial: UUID,

    @Column(name = "nome")
    val nome: String,

    @Column(name = "email")
    val email: String,
)
