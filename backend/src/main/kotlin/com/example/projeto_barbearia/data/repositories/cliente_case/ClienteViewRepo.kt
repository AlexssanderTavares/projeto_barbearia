package com.example.projeto_barbearia.data.repositories.cliente_case

import com.example.projeto_barbearia.data.models.views.ClienteView
import com.example.projeto_barbearia.data.repositories.ReadOnlyRepository
import java.util.Optional
import java.util.UUID

interface ClienteViewRepo: ReadOnlyRepository<ClienteView, UUID> {

    override fun findById(id: UUID) : Optional<ClienteView>

    override fun findAll(): List<ClienteView>
}