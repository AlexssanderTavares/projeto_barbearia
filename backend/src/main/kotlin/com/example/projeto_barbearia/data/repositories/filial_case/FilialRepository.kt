package com.example.projeto_barbearia.data.repositories.filial_case

import com.example.projeto_barbearia.data.models.Filial
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface FilialRepository: JpaRepository<Filial, UUID> {

    fun findByEmail(email: String) : Optional<Filial>
}