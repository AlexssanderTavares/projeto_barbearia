package com.example.projeto_barbearia.services.implementations

import com.example.projeto_barbearia.config.contracts.UserService
import com.example.projeto_barbearia.controllers.dtos.cliente.requests.ClienteCreateRequestDTO
import com.example.projeto_barbearia.controllers.dtos.cliente.response.ClienteCreationResponseDTO
import com.example.projeto_barbearia.controllers.dtos.user.UserCreationRequest
import com.example.projeto_barbearia.controllers.dtos.user.UserCreationResponse
import com.example.projeto_barbearia.services.utils.verifiers.EmailPatternVerifier
import com.example.projeto_barbearia.services.utils.verifiers.PasswordPatternVerifier
import com.example.projeto_barbearia.services.utils.verifiers.PatternVerifier
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.data.repositories.cliente_case.ClienteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
class ClientServiceImpl(@Autowired private val repo: ClienteRepository) {

    private lateinit var verifier: PatternVerifier

    fun create(cliente: Cliente): ClienteCreationResponseDTO? {
        println("Verifying email...")
        verifier = EmailPatternVerifier()
        val r1: Boolean = verifier.verify(cliente.email)
        println("Email verification completion result: $r1")

        println("Verifying password...")
        verifier = PasswordPatternVerifier()
        val r2: Boolean = verifier.verify(cliente.pass)
        println("Password verification completion result: $r2")

        val answer: Boolean = r1 && r2
        println("Created: $answer")

        repo.saveAndFlush(Cliente(name = cliente.name, email = cliente.email, pass = cliente.pass))
        return if (answer) ClienteCreationResponseDTO(cliente.name, cliente.email, answer) else null
    }

    fun getById(id: UUID): Cliente {
        val client: Optional<Cliente> = repo.findById(id)
        return if (client.isPresent) client.get() else throw ClassNotFoundException("Cliente not found or doesn't exist")
    }

    fun getAll(): ArrayList<Cliente> {

        val list: List<Cliente> = repo.findAll()
        println("Getting: ${list} | with size of: ${list.size}")
        val clients: List<Cliente> = list
        val resList: ArrayList<Cliente> = arrayListOf()
        clients.forEach {
            resList.add(it)
        }


        return resList
    }

    fun getByEmail(email: String): Cliente? {
        println("Trying to find a register with such data...")

        var cv: Cliente? = null
        getAll().forEach {
            println(it.toString())
            if (it.email == email) {
                println(it.toString())
                cv = it
            }
        }

        return cv
    }

    fun delete(cliente: ClienteCreateRequestDTO): Int {
            var res: Int = 0
            try {
                val cliente: Cliente = getByEmail(cliente.email).let {it ->
                    getById(it!!.id!!)
                }

                repo.delete(cliente)
                repo.flush()
                res = 1

            } catch (e: IllegalArgumentException) {
                res = -1
            } catch (e: NoSuchElementException) {
                res = -1
            }

        return res
    }

    fun update(id: UUID, data: ClienteCreateRequestDTO) : Int {

        var res: Int = 0
        val cl: Optional<Cliente> = repo.findById(id)

        if(cl.isPresent){

            val target: Cliente = cl.get()


            when{
                target.email != data.email -> {
                    repo.saveAndFlush(Cliente(id, name = target.name, email = data.email, pass = target.pass))
                    res = 1
                }

                target.name != data.name -> {
                    repo.saveAndFlush(Cliente(id, data.name, email = target.email, pass = target.pass))
                    res = 1
                }

                target.pass != data.pass -> {
                    repo.saveAndFlush(Cliente(id, target.name, email = target.email, pass = data.pass))
                    res = 1
                }
                else -> res = 0
            }
        }

        return res
    }

}