package com.example.projeto_barbearia.services.abstracts

import com.example.projeto_barbearia.data.models.OrdemServico
import com.example.projeto_barbearia.data.models.views.OSView
import java.time.LocalDateTime
import java.util.Optional

interface OrdemServicoService {

    fun create(os: OrdemServico): Int

    fun getById(id: Long): Optional<OSView>

    fun getAll(): List<OSView>

    fun delete(os: OrdemServico): Int

    fun update(os: OrdemServico, newDate: LocalDateTime): Int
}