package com.example.projeto_barbearia.services.abstracts

import com.example.projeto_barbearia.data.dtos.cliente.requests.ClienteRequestDTO
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.data.models.views.ClienteView
import java.util.UUID

interface ClientService {

    suspend fun create(cliente: ClienteRequestDTO): Int

    suspend fun getById(id: UUID): Cliente?

    suspend fun getAll(): ArrayList<ClienteView>

    suspend fun getByEmail(email: String): ClienteView?

    suspend fun delete(cliente: ClienteRequestDTO): Int

    suspend fun update(id: UUID, data: ClienteRequestDTO): Int

}