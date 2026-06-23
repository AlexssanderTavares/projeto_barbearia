package com.example.projeto_barbearia.services.implementations

import com.example.projeto_barbearia.data.dtos.cliente.requests.ClienteRequestDTO
import com.example.projeto_barbearia.services.utils.verifiers.EmailPatternVerifier
import com.example.projeto_barbearia.services.utils.verifiers.PasswordPatternVerifier
import com.example.projeto_barbearia.services.utils.verifiers.PatternVerifier
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.data.models.views.ClienteView
import com.example.projeto_barbearia.data.repositories.cliente_case.ClienteRepository
import com.example.projeto_barbearia.data.repositories.cliente_case.ClienteViewRepo
import com.example.projeto_barbearia.services.abstracts.ClientService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException

@Service
class ClientServiceImpl(@Autowired private val repo: ClienteRepository, @Autowired private val viewRepo: ClienteViewRepo) : ClientService {

    private lateinit var verifier: PatternVerifier

    override suspend fun create(cliente: ClienteRequestDTO): Int {
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

            println("Created: ${answer}")
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

        return createClientTask.await()
    }

    override suspend fun getById(id: UUID): Cliente {
        val client: Optional<Cliente> = repo.findById(id)
        return if (client.isPresent) client.get() else throw ClassNotFoundException("Cliente not found or doesn't exist")
    }

    override suspend fun getAll(): ArrayList<ClienteView> {
        val retriveEveryClientsTask: Deferred<ArrayList<ClienteView>> = CoroutineScope(Dispatchers.IO).async{
            val list: List<ClienteView> = viewRepo.findAll()
            println("Getting: ${list} | with size of: ${list.size}")
            val clients: List<ClienteView> = list
            val resList: ArrayList<ClienteView> = arrayListOf()
            clients.forEach {
                resList.add(it)
            }
            resList
        }

        return retriveEveryClientsTask.await()
    }

    override suspend fun getByEmail(email: String): ClienteView? {
        println("Trying to find a register with such data...")

        val searchForClientByEmailTask: Deferred<ClienteView?> = CoroutineScope(Dispatchers.IO).async {
            var cv: ClienteView? = null
            getAll().forEach {
                println(it.toString())
                if (it.email == email) {
                    println(it.toString())
                    cv = it
                }
            }
            cv
        }

        return searchForClientByEmailTask.await()
    }

    override suspend fun delete(cliente: ClienteRequestDTO): Int {
        val scope: Deferred<Int> = CoroutineScope(Dispatchers.IO).async {
            var res: Int = 0
            try {
                val cliente: Cliente = getByEmail(cliente.email).let {it: ClienteView? ->
                    getById(it!!.id_cliente)
                }

                repo.delete(cliente)
                repo.flush()
                res = 1

            } catch (e: IllegalArgumentException) {
                res = -1
                coroutineContext.cancel(CancellationException("Argumento inválido na chamada do método delete"))
            } catch (e: NoSuchElementException) {
                res = -1
                coroutineContext.cancel(CancellationException("Registro não existe"))
            }

            res
        }

        return scope.await()
    }

    override suspend fun update(id: UUID, data: ClienteRequestDTO) : Int {

        val updateClienteTask: Deferred<Int> = CoroutineScope(Dispatchers.IO).async {

                var res: Int = 0
                val cl: Optional<Cliente> = repo.findById(id)

                if(cl.isPresent){

                    val target: Cliente = cl.get()


                    when{
                        target.email != data.email -> {
                            repo.saveAndFlush(Cliente(id, target.name, data.email, target.pass))
                            res = 1
                        }

                        target.name != data.name -> {
                            repo.saveAndFlush(Cliente(id, data.name, target.email, target.pass))
                            res = 1
                        }

                        target.pass != data.pass -> {
                            repo.saveAndFlush(Cliente(id, target.name, target.email, data.pass))
                            res = 1
                        }
                        else -> res = 0
                    }
                }
            res
        }

        return updateClienteTask.await()
    }

}