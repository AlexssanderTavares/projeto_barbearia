package com.project.barbearia.services.abstracts

import com.project.barbearia.data.models.TipoServico
import com.project.barbearia.data.models.views.ServicoView
import java.util.Optional

interface TipoServicoService {

    fun create(servico: TipoServico): Int

    fun getById(id: Int) : Optional<ServicoView>

    fun getAll() : List<ServicoView>

    fun delete(servico: TipoServico) : Int

    fun update(servico: TipoServico, edit: TipoServico) : Int
}