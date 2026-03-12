package com.project.barbearia.services.abstracts

import com.project.barbearia.data.models.Filial
import com.project.barbearia.data.models.views.FilialView
import java.util.Optional
import java.util.UUID

interface FilialService {

    fun create(filial: Filial): Int

    fun getById(id: UUID): Optional<FilialView>

    fun getByCnpj(cnpj: String): Optional<FilialView>

    fun getByEmail(email: String): Optional<FilialView>

    fun getAll(): List<FilialView>

    fun delete(filial: Filial): Int

    fun updateQuantity(filial: Filial, quantity: Int): Int
}