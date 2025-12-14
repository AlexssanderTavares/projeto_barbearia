package com.project.barbearia.data.models.views

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable
import java.util.UUID

@Immutable
@Entity
@Table(name = "Profissional_View")
data class ProfissionalView(

    @Id
    @Column(name = "id_prof")
    val IdProf: UUID,

    @Column(name = "nome")
    val nome: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "telefone")
    val contato: String
)
