package com.example.projeto_barbearia.data.repositories.filial_case

import com.example.projeto_barbearia.data.models.views.FilialView
import com.example.projeto_barbearia.data.repositories.ReadOnlyRepository
import java.util.Optional
import java.util.UUID

interface FilialViewRepository: ReadOnlyRepository<FilialView, UUID> {

    override fun findById(id: UUID) : Optional<FilialView>

    override fun findAll(): List<FilialView>
}