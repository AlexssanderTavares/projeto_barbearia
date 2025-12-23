package com.project.barbearia.services.implementations

import com.project.barbearia.data.models.TipoServico
import com.project.barbearia.data.models.views.ServicoView
import com.project.barbearia.data.repositories.TipoServicoRepository
import com.project.barbearia.data.repositories.TipoServicoViewRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional
import kotlin.coroutines.cancellation.CancellationException

@Service
class TipoServicoServiceImpl(@Autowired private val repo: TipoServicoRepository, @Autowired val viewRepo: TipoServicoViewRepository) {

    fun create(servico: TipoServico): Int {
        var res: Int = 0

        val scope: Job = CoroutineScope(Dispatchers.IO).launch{
            try{
                if(!servico.name.equals("") && !servico.description.equals("")) {
                        repo.save(servico)
                        repo.flush()
                        res = 1
                }
            } catch (e: IllegalArgumentException) {
                res = -1
                coroutineContext.cancel(CancellationException("Argumento não é valido na chamada da função", e))
            }
        }

        scope.start()

        return when{
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }
    }

    fun getById(id: Int) : Optional<ServicoView> {
        return viewRepo.findById(id)
    }

    fun getAll() : List<ServicoView> {
        return viewRepo.findAll()
    }

    fun delete(servico: TipoServico) : Int {
        var res: Int = 0

        val scope: Job = CoroutineScope(Dispatchers.IO).launch{
            try{
                repo.delete(servico)
                repo.flush()
                res = 1
            } catch (e: NoSuchElementException) {
                res = -1
                coroutineContext.cancel(CancellationException("Registro não existe para ser deletado", e))
            } catch (e: IllegalArgumentException) {
                res = -1
                coroutineContext.cancel(CancellationException("Argumento não é váliudo para a chamada da função", e))
            }
        }

        scope.start()

        return when {
            scope.isCompleted -> res
            scope.isCompleted -> res
            else -> 0
        }
    }

    fun update(servico: TipoServico, edit: TipoServico) : Int {
        var res: Int = 0

        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            when {
                !servico.name.equals(edit.name) -> {
                    repo.save(edit)
                    repo.flush()
                    res = 1
                }

                !servico.description.equals(edit.description) -> {
                    repo.save(edit)
                    repo.flush()
                    res = 1
                }

                servico.quantity != edit.quantity -> {
                    repo.save(edit)
                    repo.flush()
                    res = 1
                }

                servico.duration != edit.duration -> {
                    repo.save(edit)
                    repo.flush()
                    res = 1
                }

                servico.price != edit.price -> {
                    repo.save(edit)
                    repo.flush()
                    res = 1
                }

                else -> coroutineContext.cancel(CancellationException(""))
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