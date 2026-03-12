package com.project.barbearia.data.models.views

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable
import java.math.BigDecimal

@Immutable
@Entity
@Table(name = "servico_View")
data class ServicoView(
    @Id
    @Column(name = "id_servico")
    val id: Int,

    @Column(name = "nome")
    val name: String,

    @Column(name = "duracao")
    val duration: Int,

    @Column(name = "preco")
    val price: BigDecimal,

    @Column(name = "descricao")
    val description: String
)
