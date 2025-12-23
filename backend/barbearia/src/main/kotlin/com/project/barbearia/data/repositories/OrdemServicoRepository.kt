package com.project.barbearia.data.repositories

import com.project.barbearia.data.models.OrdemServico
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface OrdemServicoRepository : JpaRepository<OrdemServico, Int>{
}