package com.example.projeto_barbearia.data.repositories.cliente_case

import com.example.projeto_barbearia.data.models.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ClienteRepository : JpaRepository<Cliente, UUID> {
}