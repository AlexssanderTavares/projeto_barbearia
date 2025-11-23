package com.project.barbearia.data.repositories

import com.project.barbearia.data.models.FilialTel
import org.springframework.data.jpa.repository.JpaRepository

interface FilialTelRepository : JpaRepository<FilialTel, Int> {
}