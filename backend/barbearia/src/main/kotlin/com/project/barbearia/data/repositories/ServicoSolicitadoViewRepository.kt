package com.project.barbearia.data.repositories

import com.project.barbearia.data.models.views.Agendamento

interface ServicoSolicitadoViewRepository: ReadOnlyRepository<Agendamento, Int> {
}