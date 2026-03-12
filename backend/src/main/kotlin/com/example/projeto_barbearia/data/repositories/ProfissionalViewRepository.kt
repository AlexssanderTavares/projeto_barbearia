package com.project.barbearia.data.repositories

import com.project.barbearia.data.models.Profissional
import com.project.barbearia.data.models.views.ProfissionalView
import java.util.Optional
import java.util.UUID

interface ProfissionalViewRepository: ReadOnlyRepository<ProfissionalView, UUID> {

    fun findByEmail(email: String): Optional<ProfissionalView>
}