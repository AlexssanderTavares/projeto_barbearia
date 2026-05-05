package com.project.barbearia.services.abstracts

import com.project.barbearia.data.models.OrdemServico
import com.project.barbearia.data.models.views.OSView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.time.DateTimeException
import java.time.LocalDateTime
import java.util.Optional

interface OrdemServicoService {

    suspend fun create(os: OrdemServico): Int

    suspend fun getById(id: Long): Optional<OSView>

    suspend fun getAll(): List<OSView>

    suspend fun delete(os: OrdemServico): Int

    suspend fun update(os: OrdemServico, newDate: LocalDateTime): Int
}