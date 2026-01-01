package com.project.barbearia.services.implementations

import com.project.barbearia.data.repositories.AssocOSServicoRepository
import com.project.barbearia.data.repositories.ServicoSolicitadoViewRepository
import com.project.barbearia.services.abstracts.OrdemServicoService
import com.project.barbearia.services.abstracts.TipoServicoService
import org.springframework.beans.factory.annotation.Autowired

class AssocOSServicoServiceImpl(@Autowired repo: AssocOSServicoRepository, @Autowired viewRepo: ServicoSolicitadoViewRepository, val tipoServico: TipoServicoService, val ordemServico: OrdemServicoService) {


}