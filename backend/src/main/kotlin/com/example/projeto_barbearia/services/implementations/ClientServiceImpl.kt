package com.example.projeto_barbearia.services.implementations

import com.example.projeto_barbearia.config.contracts.UserService
import com.example.projeto_barbearia.config.contracts.UserStrategy
import com.example.projeto_barbearia.controllers.dtos.user.UserCreationResponse
import com.example.projeto_barbearia.controllers.dtos.user.UserUpdateRequest
import com.example.projeto_barbearia.utils.verifiers.EmailPatternVerifier
import com.example.projeto_barbearia.utils.verifiers.PasswordPatternVerifier
import com.example.projeto_barbearia.utils.verifiers.PatternVerifier
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.data.repositories.cliente_case.ClienteRepository
import com.example.projeto_barbearia.utils.tools.TimeGatherer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
class ClientServiceImpl(@Autowired private val repo: ClienteRepository) : UserService {

    private lateinit var verifier: PatternVerifier


    override fun create(user: UserStrategy): UserCreationResponse? {
        println("Verifying type...")
        val r0: Boolean = user is Cliente

        println("Verifying email...")
        verifier = EmailPatternVerifier()
        val r1: Boolean = verifier.verify(user.email)
        println("Email verification completion result: $r1")

        println("Verifying password...")
        verifier = PasswordPatternVerifier()
        val r2: Boolean = verifier.verify(user.pass)
        println("Password verification completion result: $r2")

        val answer: Boolean = r0 && r1 && r2

        return if(answer) {
            val res: Cliente = repo.saveAndFlush(Cliente(name = user.name, email = user.email, pass = user.pass))
            println("Created: $answer")
            UserCreationResponse(email = res.email, created = answer, createdAt = TimeGatherer.getDateAndTime())
        } else {
            null
        }
    }

    override fun getById(id: UUID): Cliente? {
        val client: Optional<Cliente> = repo.findById(id)
        return if (client.isPresent) client.get() else throw ClassNotFoundException("Cliente not found or doesn't exist")
    }

    override fun getByNationalCertificate(certificate: String): Cliente? {
        TODO("Not yet implemented")
    }

    override fun getAll(): ArrayList<UserStrategy> {

        val list: List<Cliente> = repo.findAll()
        println("Getting: ${list} | with size of: ${list.size}")
        val clients: List<Cliente> = list
        val resList: ArrayList<UserStrategy> = arrayListOf()
        clients.forEach {
            resList.add(it)
        }

        return resList
    }

    override fun getByEmail(email: String): Cliente? {

        var cv: Cliente? = null
        repo.findAll().forEach {
            if (it.email == email) {
                cv = it
            }
        }

        return cv
    }

    override fun delete(user: UserStrategy): Int {
            var res: Int = 0
            try {
                val cliente: Cliente = getByEmail(user.email)!!

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

    override fun update(id: UUID, data: UserUpdateRequest) : Int {

        var res: Int = 0

        val cl: Optional<Cliente> = repo.findById(id)

        val target: Cliente = if(cl.isPresent) cl.get() else throw ClassNotFoundException("Cliente not found or doesn't exist")

        if(target.email != data.newEmail!!){
            repo.saveAndFlush(Cliente(id, name = target.name, email = data.newEmail, pass = target.pass))
            res = 1
        }

        if(target.pass != data.newPass) {
            repo.saveAndFlush(Cliente(id, target.name, email = target.email, pass = data.newPass!!))
            res = 1
        }

        return res
    }

}