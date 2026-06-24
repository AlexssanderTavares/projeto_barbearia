package com.example.projeto_barbearia.services.abstracts

import com.example.projeto_barbearia.config.contracts.UserService
import com.example.projeto_barbearia.data.dtos.cliente.requests.ClienteRequestDTO
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.data.models.views.ClienteView
import java.util.UUID

interface ClientService : UserService {

    fun create(cliente: ClienteRequestDTO): Int

    fun getById(id: UUID): Cliente?

    fun getAll(): ArrayList<Cliente>

    override fun getByEmail(email: String): Cliente?

    fun delete(cliente: ClienteRequestDTO): Int

    fun update(id: UUID, data: ClienteRequestDTO): Int

}