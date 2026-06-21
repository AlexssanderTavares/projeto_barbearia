package com.example.projeto_barbearia.services.abstracts

import com.example.projeto_barbearia.data.dtos.filial.FilialRequestDTO
import com.example.projeto_barbearia.data.models.Filial
import com.example.projeto_barbearia.data.models.views.FilialView
import com.example.projeto_barbearia.data.repositories.filial_case.FilialRepository
import java.util.UUID

interface FilialService {

    suspend fun create(filial: FilialRequestDTO): Int

    suspend fun getById(id: UUID): Filial?

    suspend fun getByCnpj(cnpj: String): FilialView?

    suspend fun getByEmail(email: String): FilialView?

    suspend fun getAll(): ArrayList<FilialView>

    suspend fun delete(filial: FilialRequestDTO): Int

    suspend fun update(id: UUID, data: FilialRequestDTO): Int
}