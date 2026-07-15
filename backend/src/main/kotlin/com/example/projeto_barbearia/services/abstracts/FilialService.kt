package com.example.projeto_barbearia.services.abstracts

import com.example.projeto_barbearia.controllers.dtos.filial.requests.FilialRequestDTO
import com.example.projeto_barbearia.data.models.Filial
import java.util.UUID

interface FilialService {

    fun create(filial: FilialRequestDTO): Int

    fun getById(id: UUID): Filial?

    fun getByCnpj(cnpj: String): Filial?

    fun getByEmail(email: String): Filial?

    fun getAll(): ArrayList<Filial>

    fun delete(filial: FilialRequestDTO): Int

    fun update(id: UUID, data: FilialRequestDTO): Int
}