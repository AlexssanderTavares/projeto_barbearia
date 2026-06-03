package com.example.projeto_barbearia.services.abstracts

import com.example.projeto_barbearia.data.dtos.cliente.ClienteCreationDTO
import com.example.projeto_barbearia.data.dtos.cliente.ClienteView
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.data.models.views.Cliente_View
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

interface ClientService {

    suspend fun create(cliente: ClienteCreationDTO): Int

    suspend fun getById(id: UUID): ClienteView?

    suspend fun getAll(): ArrayList<Cliente_View>

    suspend fun getByEmail(email: String): Cliente_View?

    suspend fun delete(cliente: Cliente): Int

    suspend fun updatePass(cliente: Cliente): Int

    suspend fun updateEmail(cliente: Cliente): Int

    suspend fun updateCep(cliente: Cliente): Int
}