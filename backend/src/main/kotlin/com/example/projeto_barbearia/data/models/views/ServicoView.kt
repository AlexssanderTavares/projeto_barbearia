package com.example.projeto_barbearia.data.models.views

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable
import java.math.BigDecimal

@Entity
@Table(name = "servico_view")
@Immutable
data class ServicoView(
    @Id
    @Column(name = "id_servico")
    val id: Long,

    @Column(name = "nome")
    val name: String,

    @Column(name = "duracao")
    val duration: Int,

    @Column(name = "preco")
    val price: BigDecimal,

    @Column(name = "descricao")
    val description: String
)
