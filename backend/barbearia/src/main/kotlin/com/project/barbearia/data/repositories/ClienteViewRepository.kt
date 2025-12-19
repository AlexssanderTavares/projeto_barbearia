package com.project.barbearia.data.repositories

import com.project.barbearia.data.models.Cliente
import com.project.barbearia.data.models.views.ClientView
import java.util.Optional
import java.util.UUID

interface ClienteViewRepository: ReadOnlyRepository<ClientView, UUID> {

    fun findByEmail(email: String): Optional<ClientView>
}