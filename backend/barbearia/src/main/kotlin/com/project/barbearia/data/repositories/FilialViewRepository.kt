package com.project.barbearia.data.repositories

import com.project.barbearia.data.models.views.ClientView
import com.project.barbearia.data.models.views.FilialView
import java.util.UUID

interface FilialViewRepository: ReadOnlyRepository<FilialView, UUID> {
    fun findByFilialId(filial_id: UUID): FilialView?

    fun findByEmail(email: String): FilialView?

    fun findByCNPJ(cnpj: String): FilialView?
}