package com.project.barbearia.data.repositories

import com.project.barbearia.data.models.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ClienteRepository : JpaRepository<Cliente, UUID> {
}