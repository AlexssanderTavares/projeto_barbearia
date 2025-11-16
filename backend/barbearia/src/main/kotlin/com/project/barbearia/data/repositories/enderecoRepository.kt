package com.project.barbearia.data.repositories

import com.project.barbearia.data.models.Endereco
import org.springframework.data.jpa.repository.JpaRepository

interface enderecoRepository : JpaRepository<Endereco, String> {
}