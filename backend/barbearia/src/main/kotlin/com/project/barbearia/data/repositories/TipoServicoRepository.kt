package com.project.barbearia.data.repositories

import com.project.barbearia.data.models.TipoServico
import org.springframework.data.jpa.repository.JpaRepository

interface TipoServicoRepository : JpaRepository<TipoServico, Int> {
}