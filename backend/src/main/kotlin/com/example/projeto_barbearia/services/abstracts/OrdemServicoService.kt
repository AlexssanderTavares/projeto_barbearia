package com.example.projeto_barbearia.services.abstracts

import com.example.projeto_barbearia.data.models.OrdemServico
import com.example.projeto_barbearia.data.models.views.OSView
import java.time.LocalDateTime
import java.util.Optional

interface OrdemServicoService {

    suspend fun create(os: OrdemServico): Int

    suspend fun getById(id: Long): Optional<OSView>

    suspend fun getAll(): List<OSView>

    suspend fun delete(os: OrdemServico): Int

    suspend fun update(os: OrdemServico, newDate: LocalDateTime): Int
}