package com.project.barbearia.services.abstracts

import com.project.barbearia.data.models.Profissional
import com.project.barbearia.data.models.views.ProfissionalView
import java.util.Optional
import java.util.UUID

interface ProfissionalService {

    suspend fun create(profissional: Profissional) : Int

    suspend fun getByEmail(email: String) : Optional<ProfissionalView>

    suspend  fun getById(id: UUID) : Optional<ProfissionalView>

    suspend fun getAll() : List<ProfissionalView>

    suspend fun delete(profissional: Profissional): Int

    suspend fun updateEmail(profissional: Profissional) : Int
}