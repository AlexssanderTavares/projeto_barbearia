package com.project.barbearia.data.repositories

import com.project.barbearia.data.models.ClientTel
import org.springframework.data.jpa.repository.JpaRepository

interface ClientTelRepository : JpaRepository<ClientTel, Int> {
}