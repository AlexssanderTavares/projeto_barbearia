package com.example.projeto_barbearia.services.abstracts

import com.example.projeto_barbearia.data.dtos.cliente.ClienteRequestDTO
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.data.models.views.Cliente_View
import java.util.UUID

interface ClientService {

    suspend fun create(cliente: ClienteRequestDTO): Int

    suspend fun getById(id: UUID): Cliente?

    suspend fun getAll(): ArrayList<Cliente_View>

    suspend fun getByEmail(email: String): Cliente_View?

    suspend fun delete(cliente: ClienteRequestDTO): Int

    suspend fun update(id: UUID, cliente: ClienteRequestDTO): Int

}