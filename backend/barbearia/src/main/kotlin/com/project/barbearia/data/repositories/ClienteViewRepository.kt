package com.project.barbearia.data.repositories

import com.project.barbearia.data.models.Cliente
import com.project.barbearia.data.models.views.ClientView
import java.util.UUID

interface ClienteViewRepository : ReadOnlyRepository<ClientView, UUID> {

    /*fun findByClienteId(id: UUID): ClientView?

    fun findByClienteEmail(email: String): ClientView?*/

    fun findAllClientes(): List<ClientView>
}