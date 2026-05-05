package com.project.barbearia.services.implementations

import com.example.projeto_barbearia.data.DTOs.Cliente.ClienteCreationDTO
import com.example.projeto_barbearia.services.utils.verifiers.EmailPatternVerifier
import com.example.projeto_barbearia.services.utils.verifiers.PasswordPatternVerifier
import com.example.projeto_barbearia.services.utils.verifiers.PatternVerifier
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.Duration

@Service
class ClientServiceImpl(@Autowired private val repo: ClienteRepository, @Autowired private val viewRepo: ClienteViewRepository) : ClientService {

    private val checker: UniqueDataChecker = UniqueDataChecker()
    private lateinit var verifier: PatternVerifier

    override suspend fun create(cliente: ClienteCreationDTO): Int {
        var res: Int = 0
        val scope: Job = CoroutineScope(Dispatchers.IO).launch {

            val res1: Deferred<Boolean> = async {
                println("Verifying email...")
                verifier = EmailPatternVerifier()
                val r1: Boolean = verifier.verify(cliente.email) && getByEmail(cliente.email).isEmpty
                println("Email verification completion result: $r1")
                r1
            }

            val res2: Deferred<Boolean> = async {
                println("Verifying password...")
                verifier = PasswordPatternVerifier()
                val r2: Boolean = verifier.verify(cliente.pass)
                println("Password verification completion result: $r2")
                r2
            }

            /*val res3: Deferred<Boolean?> = async {
                var r3: Boolean? = null
                println("Verifying postal code...")
                if(cliente.cep != null) {
                 r3 = verifier.verify(cliente.cep!!)
                }
                println("Postal code verification completion result: $r3")
                r3
            }*/

            val answer: Boolean = res1.await() && res2.await()

            println("Answer: ${answer}")

            if (answer) {
                repo.save(Cliente(null, cliente.name, cliente.email, cliente.pass, null))
                repo.flush()
                res = 1
            } else {
                res = -1
            }
        }

        scope.start()

        println("Coroutine started? ${scope.isActive}")

        delay(2000)

        return if(scope.isCompleted){
            res
        } else {
            res
        }
    }

    override suspend fun getById(id: UUID): Optional<ClientView> {
        return viewRepo.findById(id)
    }

    override suspend fun getByEmail(email: String): Optional<ClientView> {
        return viewRepo.findByEmail(email)
    }

    override suspend fun getAll(): List<ClientView> {
        return viewRepo.findAll()
    }

    override suspend fun delete(cliente: Cliente): Int {
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
        return when {
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }
    }

    override suspend fun updatePass(cliente: Cliente): Int {
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

        return when {
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }
    }

    override suspend fun updateEmail(cliente: Cliente): Int {
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

        return when {
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }
    }

    override suspend fun updateCep(cliente: Cliente): Int {
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

        return when {
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }
    }

}