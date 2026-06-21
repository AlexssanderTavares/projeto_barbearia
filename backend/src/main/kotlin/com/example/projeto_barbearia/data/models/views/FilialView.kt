package com.example.projeto_barbearia.data.models.views

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable
import java.util.UUID


@Entity
@Table(name = "filial_view")
@Immutable
data class FilialView(
    @Id
    @Column(name = "id_filial")
    val id: UUID,

    @Column(name = "nome")
    val name: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "cnpj")
    val cnpj: String,

    @Column(name = "qtd_profissionais")
    val QtProf: Int,
)
