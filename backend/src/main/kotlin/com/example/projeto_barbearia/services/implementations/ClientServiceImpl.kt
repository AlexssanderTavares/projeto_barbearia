package com.project.barbearia.services.implementations

import com.project.barbearia.data.models.Cliente
import com.project.barbearia.data.models.views.ClientView
import com.project.barbearia.data.repositories.ClienteRepository
import com.project.barbearia.data.repositories.ClienteViewRepository
import com.project.barbearia.services.abstracts.ClientService
import com.project.barbearia.services.utils.UniqueDataChecker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException

@Service
class ClientServiceImpl(@Autowired private val repo: ClienteRepository, @Autowired private val viewRepo: ClienteViewRepository): ClientService {

    private val checker: UniqueDataChecker = UniqueDataChecker()

    override fun create(cliente: Cliente): Int {
        var res: Int = 0
        val scope: Job = CoroutineScope(Dispatchers.IO).launch {

            val res1: Deferred<Boolean> = async {
                checker.verifyEmail(cliente.email) && getByEmail(cliente.email).isEmpty
            }

            val res2: Deferred<Boolean> = async {
                checker.verifyPass(cliente.pass)
            }

            val res3: Deferred<Boolean> = async {
                checker.verifyPostalCode(cliente.cep!!)
            }

            if (res1.await() && res2.await() && res3.await()) {
                repo.save(cliente)
                repo.flush()
                res = 1
            } else {
                when {
                    !res1.await() -> {
                        coroutineContext.cancel(
                            CancellationException(
                                "Entrada de texto inválida como endereço de E-Mail",
                                IllegalArgumentException()
                            )
                        )
                        res = -1
                    }

                    !res2.await() -> {
                        coroutineContext.cancel(
                            CancellationException(
                                "Senha precisa conter números, ao menos uma letra maiúscula e ao menos um símbolo não alfabético",
                                IllegalArgumentException()
                            )
                        )
                        res = -1
                    }

                    !res3.await() -> {
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

    override fun getById(id: UUID): Optional<ClientView> {
        return viewRepo.findById(id)
    }

    override fun getByEmail(email: String): Optional<ClientView> {
        return viewRepo.findByEmail(email)
    }

    override fun getAll(): List<ClientView> {
        return viewRepo.findAll()
    }

    override fun delete(cliente: Cliente): Int {
        var res: Int = 0

        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            try {
                if (cliente == getByEmail(cliente.email) || cliente.id == getById(cliente.id!!)) {
                    repo.delete(cliente)
                    repo.flush()
                    res += 1
                }
            } catch (e: IllegalArgumentException) {
                res = -1
                coroutineContext.cancel(CancellationException("Argumento inválido na chamada do método delete"))
            } catch (e: NoSuchElementException) {
                res = -1
                coroutineContext.cancel(CancellationException("Registro não existe"))
            }
        }

        scope.start()
        return when{
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }
    }

    override fun updatePass(cliente: Cliente): Int {
        var res: Int = 0
        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            try {
                if ((cliente == getByEmail(cliente.email) || cliente == getById(cliente.id!!)) && cliente.pass != repo.findById(
                        cliente.id!!
                    ).get().pass
                ) {
                    repo.save(cliente)
                    repo.flush()
                    res += 1
                }
            } catch (e: IllegalArgumentException) {
                res = -1
                coroutineContext.cancel(CancellationException("Argumento não pode ser nulo", e))
            } catch (e: NoSuchElementException) {
                res = -1
                coroutineContext.cancel(CancellationException("Elemento não existe na base de dados", e))
            }
        }

        scope.start()

        return when{
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }
    }

    override fun updateEmail(cliente: Cliente): Int {
        var res: Int = 0
        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            try {
                if ((cliente == getByEmail(cliente.email) || cliente == getById(cliente.id!!)) && cliente.pass != repo.findById(
                        cliente.id!!
                    ).get().pass
                ) {
                    repo.save(cliente)
                    repo.flush()
                    res += 1
                }
            } catch (e: IllegalArgumentException) {
                res = -1
                coroutineContext.cancel(CancellationException("Argumento não pode ser nulo", e))
            } catch (e: NoSuchElementException) {
                res = -1
                coroutineContext.cancel(CancellationException("Elemento não existe na base de dados", e))
            }
        }

        scope.start()

        return when{
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }
    }

    override fun updateCep(cliente: Cliente): Int {
        var res: Int = 0
        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            try {
                if ((cliente == getByEmail(cliente.email) || cliente == getById(cliente.id!!)) && cliente.pass != repo.findById(
                        cliente.id!!
                    ).get().pass
                ) {
                    repo.save(cliente)
                    repo.flush()
                    res += 1
                }
            } catch (e: IllegalArgumentException) {
                res = -1
                coroutineContext.cancel(CancellationException("Argumento não pode ser nulo", e))
            } catch (e: NoSuchElementException) {
                res = -1
                coroutineContext.cancel(CancellationException("Elemento não existe na base de dados", e))
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