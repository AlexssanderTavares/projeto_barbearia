package com.example.projeto_barbearia.services.abstracts

import com.example.projeto_barbearia.config.contracts.UserService
import com.example.projeto_barbearia.controllers.dtos.cliente.requests.ClienteCreateRequestDTO
import com.example.projeto_barbearia.data.models.Cliente
import java.util.UUID

interface ClientService  {

    fun create(cliente: ClienteCreateRequestDTO): Int

    fun getById(id: UUID): Cliente?

    fun getAll(): ArrayList<Cliente>

    fun getByEmail(email: String): Cliente?

    fun delete(cliente: ClienteCreateRequestDTO): Int

    fun update(id: UUID, data: ClienteCreateRequestDTO): Int

}