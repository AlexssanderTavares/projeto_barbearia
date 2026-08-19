package com.example.projeto_barbearia.services.abstracts

import com.example.projeto_barbearia.controllers.dtos.cliente.requests.ClienteCreateRequest
import com.example.projeto_barbearia.controllers.dtos.cliente.requests.ClienteUpdateRequest
import com.example.projeto_barbearia.controllers.dtos.cliente.response.ClienteCreationResponse
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.data.models.Filial
import java.util.UUID

interface ClientService  {

    fun create(cliente: Cliente): ClienteCreationResponse?

    fun getById(id: UUID): Cliente?

    fun getByNationalCertificate(certificate: String): Cliente?

    fun getAll(): ArrayList<Cliente>

    fun getByEmail(email: String): Cliente?

    fun delete(cliente: Cliente): Int

    fun update(id: UUID, data: ClienteUpdateRequest): Int

}