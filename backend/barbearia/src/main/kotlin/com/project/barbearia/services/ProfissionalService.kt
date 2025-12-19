package com.project.barbearia.services

import com.project.barbearia.data.models.Profissional
import com.project.barbearia.data.models.views.ProfissionalView
import com.project.barbearia.data.repositories.ProfissionalRepository
import com.project.barbearia.data.repositories.ProfissionalViewRepository
import com.project.barbearia.services.utils.UniqueDataChecker
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
class ProfissionalService(@Autowired private val repo: ProfissionalRepository, @Autowired private val viewRepo: ProfissionalViewRepository) {

    private val checker: UniqueDataChecker = UniqueDataChecker()

    fun create(profissional: Profissional) : Int{
        var res: Int = 0

        val scope: Job = CoroutineScope(Dispatchers.IO).launch {

            if(checker.verifyEmail(profissional.email) && getByEmail(profissional.email).isEmpty()){
                repo.save(profissional)
                repo.flush()
                res = 1
            } else {
                res = -1
                coroutineContext.cancel(CancellationException("Entrada de texto não é valida como email.",
                    IllegalArgumentException()))
            }
        }

        scope.start()

        return when{
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }
    }

    fun getByEmail(email: String) : Optional<ProfissionalView> {
        return viewRepo.findByEmail(email)
    }

    fun getAll() : List<ProfissionalView>{
        return viewRepo.findAll()
    }

    fun delete(profissional: Profissional): Int {
        var res: Int = 0

        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            try {
                if (getByEmail(profissional.email) == profissional || profissional == viewRepo.findById(profissional.id)) {
                    repo.delete(profissional)
                    repo.flush()
                    res = 1
                }
            } catch (e: IllegalArgumentException) {
                res = -1
                coroutineContext.cancel(CancellationException("Registro não existe", e))
            }
        }

        scope.start()

        return when{
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }
    }

    fun updateEmail(profissional: Profissional) : Int {
        var res: Int = 0

        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            try{
                if (getByEmail(profissional.email) == profissional || profissional == viewRepo.findById(profissional.id)){
                    repo.save(profissional)
                    repo.flush()
                    res = 1
                }
            } catch (e: IllegalArgumentException) {
                res = -1
                coroutineContext.cancel(CancellationException("Registro não existe", e))
            }
        }

        scope.start()

        return when{
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }
    }
}