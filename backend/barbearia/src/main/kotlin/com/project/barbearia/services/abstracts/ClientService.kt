package com.project.barbearia.services.abstracts

import com.project.barbearia.data.models.Cliente
import com.project.barbearia.data.models.views.ClientView
import java.util.Optional
import java.util.UUID

interface ClientService {

    fun create(cliente: Cliente): Int

    fun getById(id: UUID): Optional<ClientView>

    fun getByEmail(email: String): Optional<ClientView>

    fun getAll(): List<ClientView>

    fun delete(cliente: Cliente): Int

    fun updatePass(cliente: Cliente): Int

    fun updateEmail(cliente: Cliente): Int

    fun updateCep(cliente: Cliente): Int
}