package com.project.barbearia.data.repositories

import com.project.barbearia.data.models.ProfTel
import org.springframework.data.jpa.repository.JpaRepository

interface ProfTelRepository : JpaRepository<ProfTel, Int> {
}