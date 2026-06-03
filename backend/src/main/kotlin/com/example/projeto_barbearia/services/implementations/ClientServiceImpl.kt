package com.example.projeto_barbearia.services.implementations

import com.example.projeto_barbearia.data.dtos.cliente.ClienteCreationDTO
import com.example.projeto_barbearia.data.dtos.cliente.ClienteView
import com.example.projeto_barbearia.services.utils.verifiers.EmailPatternVerifier
import com.example.projeto_barbearia.services.utils.verifiers.PasswordPatternVerifier
import com.example.projeto_barbearia.services.utils.verifiers.PatternVerifier
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.data.models.views.Cliente_View
import com.example.projeto_barbearia.data.repositories.cliente_case.ClienteRepository
import com.example.projeto_barbearia.data.repositories.cliente_case.ClienteViewRepo
import com.example.projeto_barbearia.services.abstracts.ClientService
import jakarta.persistence.Entity
import jakarta.transaction.Transactional
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
import org.springframework.web.servlet.function.ServerResponse.async
import java.util.Optional
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException

@Service
class ClientServiceImpl(@Autowired private val repo: ClienteRepository, @Autowired private val viewRepo: ClienteViewRepo) : ClientService {

    private lateinit var verifier: PatternVerifier

    override suspend fun create(cliente: ClienteCreationDTO): Int {
        var res: Int = 0
        val createClientTask: Deferred<Int> = CoroutineScope(Dispatchers.IO).async {

            val res1: Deferred<Boolean> = async {
                println("Verifying email...")
                verifier = EmailPatternVerifier()
                val r1: Boolean = verifier.verify(cliente.email)
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

            val answer: Boolean = res1.await() && res2.await()

            println("Answer: ${answer}")
            delay(2000)
            if (answer) {
                repo.saveAndFlush(Cliente(name = cliente.name, email = cliente.email, pass = cliente.pass))
                res = 1
                res
            } else {
                res = -1
                res
            }
        }

        return if(createClientTask.await() > 0){
            res
        } else {
            res
        }
    }

    override suspend fun getById(id: UUID): ClienteView? {
        val client: Optional<Cliente> = repo.findById(id)
        val res: ClienteView = ClienteView(client.get().id!!, client.get().name, client.get().email)
        return res ?: throw ClassNotFoundException("Cliente not found")
    }

    override suspend fun getAll(): ArrayList<Cliente_View> {
        //using Client Repository
        val retriveEveryClientsTask: Deferred<ArrayList<Cliente_View>> = CoroutineScope(Dispatchers.IO).async{
            println("Getting: ${viewRepo.findAll()} | with size of: ${viewRepo.findAll().size}")
            val clients = viewRepo.findAll()
            val resList: ArrayList<Cliente_View> = arrayListOf()
            clients.forEach {
                var clientView: Cliente_View = it
                resList.add(clientView)
            }
            resList
        }

        return retriveEveryClientsTask.await()

        //Using View Repository
        //return viewRepo.findAll()
    }

    override suspend fun getByEmail(email: String): Cliente_View? {
        println("Trying to find a register with such data...")

        val searchForClientByEmailTask: Deferred<Cliente_View?> = CoroutineScope(Dispatchers.IO).async {
            var cv: Cliente_View? = null
            getAll().forEach {
                println(it.toString())
                if (it.email == email) {
                    println(it.toString())
                    cv = it
                }
            }
            cv
        }

            val res: Cliente_View = searchForClientByEmailTask.await()!!

            return if (searchForClientByEmailTask.isCompleted) {
                println("Answer: $res")
                res
            }else null
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