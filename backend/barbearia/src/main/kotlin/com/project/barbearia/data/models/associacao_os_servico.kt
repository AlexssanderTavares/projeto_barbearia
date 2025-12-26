package com.project.barbearia.data.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity(name = "associacao_os_servico")
@Table(name = "associacao_os_servico")
data class associacao_os_servico(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assoc")
    val idAssoc: Int,

    @Column(name = "id_os")
    val idOs: Int,

    @Column(name = "id_tiposervico")
    val idTipoServico: Int,

    @Column(name = "qt_solicitado")
    val qtServico: Int,

    @Column(name = "vlr_unit")
    var vlUnit: BigDecimal = BigDecimal.ZERO,

    @Column(name = "total_solicitado")
    val totalSolic: BigDecimal = BigDecimal.valueOf(vlUnit.toDouble()).multiply(qtServico.toBigDecimal()),
)