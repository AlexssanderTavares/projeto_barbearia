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
    val IdServico: Int,

    @Column(name = "nome")
    val nome: String,

    @Column(name = "duracao")
    val duracao: Int,

    @Column(name = "preco")
    val preco: BigDecimal,

    @Column(name = "descricao")
    val descricao: String
)
