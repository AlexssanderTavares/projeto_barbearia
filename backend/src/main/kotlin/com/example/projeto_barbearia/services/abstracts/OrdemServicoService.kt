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

    fun create(os: OrdemServico): Int

    fun getById(id: Long): Optional<OSView>

    fun getAll(): List<OSView>

    fun delete(os: OrdemServico): Int

    fun update(os: OrdemServico, newDate: LocalDateTime): Int
}