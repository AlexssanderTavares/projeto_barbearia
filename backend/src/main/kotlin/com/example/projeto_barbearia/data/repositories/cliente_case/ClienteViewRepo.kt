package com.example.projeto_barbearia.data.repositories.cliente_case

import com.example.projeto_barbearia.data.models.views.Cliente_View
import com.example.projeto_barbearia.data.repositories.ReadOnlyRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

interface ClienteViewRepo: ReadOnlyRepository<Cliente_View, UUID> {

    override fun findById(id: UUID) : Optional<Cliente_View>

    override fun findAll(): List<Cliente_View>
}