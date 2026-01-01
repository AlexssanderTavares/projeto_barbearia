package com.project.barbearia.data.models.views

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable
import java.math.BigDecimal

@Immutable
@Entity
@Table(name = "servico_solicitado")
data class servico_solicitado(

    @Id
    @Column(name = "id_assoc")
    val id: Int,

    @Column(name = "agendamento")
    val agendamento: String,

    @Column(name = "nome")
    val nome: String,

    @Column(name = "duracao")
    val duracao: String,

    @Column(name = "valor_unitario")
    val valorUnit: BigDecimal,

    @Column(name = "qt_solicitado")
    val qtSolicitado: Int,

    @Column(name = "subtotal")
    val subtotal: BigDecimal,

    @Column(name = "duracao_final")
    val duracaoFinal: Int

)
