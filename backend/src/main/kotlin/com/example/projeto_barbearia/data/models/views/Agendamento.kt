package com.example.projeto_barbearia.data.models.views

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import org.hibernate.annotations.Immutable
import java.math.BigDecimal
import java.util.UUID

@Immutable
@Entity
data class Agendamento(

    @Id
    @Column(name = "id_os")
    val id: Long,

    @Column(name = "data")
    val data: LocalDateTime,

    @Column(name = "status")
    val status: String,

    @Column(name = "cod_cliente")
    val codCliente: UUID,

    @Column(name = "cliente")
    val nomeCliente: String,

    @Column(name = "email_cliente")
    val emailCliente: String,

    @Column(name = "profissional")
    val nameProf: String,

    @Column(name = "final_duration")
    val duracaoFinal: Int,

    @Column(name = "subtotal")
    val subtotal: BigDecimal
)
