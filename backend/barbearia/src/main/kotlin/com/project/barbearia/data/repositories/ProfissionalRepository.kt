package com.project.barbearia.data.repositories

import com.project.barbearia.data.models.Profissional
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProfissionalRepository : JpaRepository<Profissional, UUID> {
}