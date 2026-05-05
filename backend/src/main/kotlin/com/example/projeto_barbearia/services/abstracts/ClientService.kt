package com.project.barbearia.services.abstracts

import com.example.projeto_barbearia.data.DTOs.Cliente.ClienteCreationDTO
import com.project.barbearia.data.models.Cliente
import com.project.barbearia.data.models.views.ClientView
import java.util.Optional
import java.util.UUID

interface ClientService {

   suspend fun create(cliente: ClienteCreationDTO): Int

    suspend fun getById(id: UUID): Optional<ClientView>

    suspend fun getByEmail(email: String): Optional<ClientView>

    suspend fun getAll(): List<ClientView>

    suspend fun delete(cliente: Cliente): Int

    suspend fun updatePass(cliente: Cliente): Int

    suspend fun updateEmail(cliente: Cliente): Int

    suspend fun updateCep(cliente: Cliente): Int
}