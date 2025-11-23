package com.project.barbearia.data.repositories

import com.project.barbearia.data.models.Filial
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FilialRepository : JpaRepository<Filial, UUID> {
}