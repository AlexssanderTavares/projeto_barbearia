package com.example.projeto_barbearia.services.abstracts

import com.example.projeto_barbearia.data.dtos.filial.requests.FilialRequestDTO
import com.example.projeto_barbearia.data.models.Filial
import com.example.projeto_barbearia.data.models.views.FilialView
import java.util.UUID

interface FilialService {

    fun create(filial: FilialRequestDTO): Int

    fun getById(id: UUID): Filial?

    fun getByCnpj(cnpj: String): Filial?

    fun getByEmail(email: String): Filial?

    fun getAll(): ArrayList<FilialView>

    fun delete(filial: FilialRequestDTO): Int

    fun update(id: UUID, data: FilialRequestDTO): Int
}