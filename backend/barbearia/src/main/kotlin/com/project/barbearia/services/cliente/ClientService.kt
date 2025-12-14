package com.project.barbearia.services.cliente

import com.project.barbearia.data.models.Cliente
import com.project.barbearia.data.models.views.ClientView
import com.project.barbearia.data.repositories.ClienteRepository
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
import kotlin.coroutines.cancellation.CancellationException

@Service
class ClientService(@Autowired val repository: ClienteRepository, @Autowired val viewService: ClienteViewService) {

    init{
        this.loadClients()
    }

    private val checker: UniqueDataChecker = UniqueDataChecker()

    lateinit var clientList: List<ClientView>

    fun loadClients() {
        this.clientList = viewService.getAll()
    }

    fun create(cliente: Cliente) : Int {
        var res: Int = 0
        val scope: Job = CoroutineScope(Dispatchers.IO).launch {

            val res1: Deferred<Boolean> = async {
                checker.verifyEmail(cliente.email)
            }

            val res2: Deferred<Boolean> = async {
                checker.verifyPass(cliente.pass)
            }

            val res3: Deferred<Boolean> = async {
                checker.verifyPostalCode(cliente.cep!!)
            }

            if(res1.await() && res2.await() && res3.await()){
                repository.save(cliente)
                repository.flush()
                res++
                loadClients()
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

        return if(scope.isCompleted){
            res
        }else if(scope.isCancelled){
            res
        } else {
            0
        }
    }

    fun delete(cliente: Cliente) : Int {
        var res: Int = 0

        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.delete(cliente)
                repository.flush()
                loadClients()
                res += 1
            } catch (e: IllegalArgumentException) {
                res = -1
                coroutineContext.cancel()
            }
        }

        scope.start()
        return if(scope.isCompleted){
            res
        } else if(scope.isCancelled) {
            res
        } else {
            0
        }
    }

    fun updatePass(cliente: Cliente): Int {
        var res: Int = 0
        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            try{
                val cl: Cliente = repository.findById(cliente.id!!).orElseThrow()

                if(cliente.pass != cl.pass){
                    repository.save(cliente)
                    repository.flush()
                    loadClients()
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

        return if(scope.isCompleted){
            res
        } else {
            res
        }
    }

    fun updateEmail(cliente: Cliente): Int {
        var res: Int = 0
        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            try{
                val cl: Cliente = repository.findById(cliente.id!!).orElseThrow()

                if(cliente.email != cl.email){
                    repository.save(cliente)
                    repository.flush()
                    loadClients()
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

        return if(scope.isCompleted){
            res
        } else {
            res
        }
    }

    fun updateCep(cliente: Cliente): Int {
        var res: Int = 0
        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            try{
                val cl: Cliente = repository.findById(cliente.id!!).orElseThrow()

                if(cliente.cep != cl.cep){
                    repository.save(cliente)
                    repository.flush()
                    loadClients()
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

        return if(scope.isCompleted){
            res
        } else {
            res
        }
    }

}