package com.example.projeto_barbearia.data.repositories.cliente_case

import com.example.projeto_barbearia.data.models.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface ClienteRepository : JpaRepository<Cliente, UUID> {

    fun findByEmail(email: String): Optional<Cliente>

    fun findByNationalCertificate(certificate: String): Optional<Cliente>
}