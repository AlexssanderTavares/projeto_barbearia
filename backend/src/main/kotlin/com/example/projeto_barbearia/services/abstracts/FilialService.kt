package com.example.projeto_barbearia.services.abstracts

import com.example.projeto_barbearia.data.models.Filial
import com.example.projeto_barbearia.data.models.views.FilialView
import java.util.Optional
import java.util.UUID

interface FilialService {

    suspend fun create(filial: Filial): Int

    suspend fun getById(id: UUID): Optional<FilialView>

    suspend fun getByCnpj(cnpj: String): Optional<FilialView>

    suspend fun getByEmail(email: String): Optional<FilialView>

    suspend fun getAll(): List<FilialView>

    suspend fun delete(filial: Filial): Int

    suspend fun updateQuantity(filial: Filial, quantity: Int): Int
}