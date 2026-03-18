package com.project.barbearia.services.implementations

import com.example.projeto_barbearia.services.utils.PasswordPatternVerifier
import com.project.barbearia.data.models.Filial
import com.project.barbearia.data.models.views.FilialView
import com.project.barbearia.data.repositories.FilialRepository
import com.project.barbearia.data.repositories.FilialViewRepository
import com.project.barbearia.services.abstracts.FilialService
import com.project.barbearia.services.utils.UniqueDataChecker
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Autowired
import java.util.Optional
import java.util.UUID

class FilialServiceImpl(@Autowired private val repo: FilialRepository, @Autowired private val viewRepo: FilialViewRepository): FilialService {

    private val checker: UniqueDataChecker = UniqueDataChecker()
    private val pswdVerifier: PasswordPatternVerifier = PasswordPatternVerifier()

    override fun create(filial: Filial): Int {
        var res: Int = 0
        val scope: Job = CoroutineScope(Dispatchers.IO).launch {

            val res1: Deferred<Boolean> = async{
                checker.verifyEmail(filial.email) && viewRepo.findByEmail(filial.email).isEmpty
            }

            val res2: Deferred<Boolean> = async {
                pswdVerifier.verify(filial.pass)
            }

            val res3: Deferred<Boolean> = async {
                checker.verifyBusinessCode(filial.cnpj)
            }

            val res4: Deferred<Boolean> = async {
                checker.verifyPostalCode(filial.cep)
            }

            if(res1.await() && res2.await() && res3.await() && res4.await()) {
                repo.save(filial)
                repo.flush()
                res = 1
            } else {
                when{
                    !res1.await() -> {
                        coroutineContext.cancel(CancellationException(
                            "Entrada de texto inválida como endereço de E-Mail",
                            IllegalArgumentException()
                        ))
                        res = -1
                    }

                    !res2.await() -> {
                        coroutineContext.cancel(CancellationException(
                            "Senha precisa conter números, ao menos uma letra maiúscula e ao menos um símbolo não alfabético",
                            IllegalArgumentException()
                        ))
                        res = -1
                    }

                    !res3.await() -> {
                        coroutineContext.cancel(CancellationException(
                            "Entrada não é válida como CNPJ",
                            IllegalArgumentException()
                        ))
                        res = -1
                    }

                    !res4.await() -> {
                        coroutineContext.cancel(
                            CancellationException(
                                "Entrada de cep inválida",
                                IllegalArgumentException()
                            )
                        )
                        res = -1
                    }
                }
            }
        }

        scope.start()
        return when{
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }
    }

    override fun getById(id: UUID): Optional<FilialView> {
        return viewRepo.findById(id)
    }

    override fun getByCnpj(cnpj: String): Optional<FilialView> {
        return viewRepo.findByCnpj(cnpj)
    }

    override fun getByEmail(email: String): Optional<FilialView> {
        return viewRepo.findByEmail(email)
    }

    override fun getAll(): List<FilialView> {
        return viewRepo.findAll()
    }

    override fun delete(filial: Filial): Int {
        var res: Int = 0

        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            try {
                if (filial == getByEmail(filial.email) || filial == getByCnpj(filial.cnpj) || filial == getById(filial.id)) {
                    repo.delete(filial)
                    repo.flush()
                    res = 1
                }
            } catch (e: IllegalArgumentException){
                res = -1
                coroutineContext.cancel(CancellationException("Argumento não é válido na chamada do método delete."))
            } catch (e: NoSuchElementException) {
                res = -1
                coroutineContext.cancel(CancellationException("Registro não existe."))
            }
        }
        scope.start()
        return when{
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }
    }

    override fun updateQuantity(filial: Filial, quantity: Int): Int {
        var res: Int = 0
        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            try {
                if ((filial == getByCnpj(filial.cnpj) || filial == getById(filial.id) || filial == getByEmail(filial.email)) && filial.qt_prof != getByEmail(
                        filial.cnpj
                    ).get().QtProf
                ) {
                    filial.qt_prof = quantity
                    repo.save(filial)
                    repo.flush()
                    res = 1
                }
            } catch (e: IllegalArgumentException) {
                res = -1
                coroutineContext.cancel(CancellationException("Um ou mais argumentos não são válidos na chamada do método update"))
            } catch (e: NoSuchElementException) {
                res = -1
                coroutineContext.cancel(CancellationException("Registro não existe."))
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