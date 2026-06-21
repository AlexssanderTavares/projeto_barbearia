package com.example.projeto_barbearia.data.models.views

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import org.hibernate.annotations.Immutable
import java.math.BigDecimal
import java.util.UUID


@Entity
@Table(name = "os_view")
@Immutable
data class OSView(

    @Id
    @Column(name = "id_os")
    val id: Long,

    @Column(name = "data")
    val data: LocalDateTime,

    @Column(name = "status")
    val status: String,

    @Column(name = "cod_cliente")
    val idCliente: UUID,

    @Column(name = "nome_cliente")
    val nomeCliente: String,

    @Column(name = "email_cliente")
    val emailCliente: String,

    @Column(name = "profissional")
    val prof: String,

    @Column(name = "servicos")
    val nomeServico: String,

    @Column(name = "descricao")
    val descricao: String,

    @Column(name = "tempo_duracao")
    val tempoDuracao: Int,

    @Column(name = "preco_servico")
    val precoServico: BigDecimal
)
