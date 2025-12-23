package com.project.barbearia.services.abstracts

import com.project.barbearia.data.models.Profissional
import com.project.barbearia.data.models.views.ProfissionalView
import java.util.Optional
import java.util.UUID

interface ProfissionalService {

    fun create(profissional: Profissional) : Int

    fun getByEmail(email: String) : Optional<ProfissionalView>

    fun getById(id: UUID) : Optional<ProfissionalView>

    fun getAll() : List<ProfissionalView>

    fun delete(profissional: Profissional): Int

    fun updateEmail(profissional: Profissional) : Int
}