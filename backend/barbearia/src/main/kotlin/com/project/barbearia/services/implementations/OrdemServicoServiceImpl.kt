package com.project.barbearia.services.implementations

import com.project.barbearia.data.models.OrdemServico
import com.project.barbearia.data.repositories.OrdemServicoRepository
import com.project.barbearia.services.abstracts.ClientService
import com.project.barbearia.services.abstracts.ProfissionalService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Autowired

class OrdemServicoServiceImpl(@Autowired private val repo: OrdemServicoRepository, private val clService: ClientService, private val profService: ProfissionalService){

    fun create(os: OrdemServico) : Int {
        var res: Int = 0

        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            try{
                if(!clService.getById(os.idClient).isEmpty && !profService.getById(os.idProf).isEmpty){
                    repo.save(os)
                    repo.flush()
                    res = 1
                } else {
                    throw NoSuchElementException()
                }
            } catch (e: NoSuchElementException) {
                res = -1
                if(clService.getById(os.idClient).isEmpty) {
                    coroutineContext.cancel()
                }
            } catch(e: NoSuchElementException) {
                res = -1
                if(profService.getById(os.idProf).isEmpty) {
                    coroutineContext.cancel()
                }
            }
        }

        scope.start()
        return when {
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }
    }
}