package com.example.projeto_barbearia.data.repositories.cliente_case

import com.project.barbearia.data.models.views.ClientView
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import java.util.Optional
import java.util.UUID

@NoRepositoryBean
interface ClienteViewRepo: CrudRepository<ClientView, UUID> {

    override fun findById(id: UUID) : Optional<ClientView>

    override fun findAll(): List<ClientView>
}