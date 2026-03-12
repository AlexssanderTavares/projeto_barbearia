package com.project.barbearia.data.repositories

import com.project.barbearia.data.models.views.FilialView
import java.util.Optional
import java.util.UUID

interface FilialViewRepository: ReadOnlyRepository<FilialView, UUID> {

    fun findByEmail(email: String): Optional<FilialView>

    fun findByCnpj(cnpj: String): Optional<FilialView>
}