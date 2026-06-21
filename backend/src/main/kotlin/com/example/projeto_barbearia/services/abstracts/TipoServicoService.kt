package com.example.projeto_barbearia.services.abstracts

import com.example.projeto_barbearia.data.models.TipoServico
import com.example.projeto_barbearia.data.models.views.ServicoView
import java.util.Optional

interface TipoServicoService {

    suspend fun create(servico: TipoServico): Int

    suspend fun getById(id: Int) : Optional<ServicoView>

    suspend fun getAll() : List<ServicoView>

    suspend fun delete(servico: TipoServico) : Int

    suspend fun update(servico: TipoServico, edit: TipoServico) : Int
}