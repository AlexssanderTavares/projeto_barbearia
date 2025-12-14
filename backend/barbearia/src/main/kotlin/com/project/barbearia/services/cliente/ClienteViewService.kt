package com.project.barbearia.services.cliente

import com.project.barbearia.data.models.views.ClientView
import com.project.barbearia.data.repositories.ClienteViewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ClienteViewService(@Autowired val repository: ClienteViewRepository) {
/*
    fun getClienteById(id: UUID): ClientView? {
        return repository.findByClienteId(id)
    }

    fun getClienteByEmail(email: String): ClientView? {
        return repository.findByClienteEmail(email)
    }*/

    fun getAll(): List<ClientView> {
        return repository.findAllClientes()
    }
}