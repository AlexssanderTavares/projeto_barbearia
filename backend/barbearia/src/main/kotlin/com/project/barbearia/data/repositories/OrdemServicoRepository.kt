package com.project.barbearia.data.repositories

import com.project.barbearia.data.models.OrdemServico
import org.springframework.data.jpa.repository.JpaRepository

interface OrdemServicoRepository : JpaRepository<OrdemServico, Int> {
}