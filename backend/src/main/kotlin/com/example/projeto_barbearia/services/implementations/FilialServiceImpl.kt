package com.example.projeto_barbearia.services.implementations

import com.example.projeto_barbearia.config.contracts.UserService
import com.example.projeto_barbearia.config.contracts.UserStrategy
import com.example.projeto_barbearia.controllers.dtos.filial.requests.FilialRequestDTO
import com.example.projeto_barbearia.controllers.dtos.filial.response.FilialCreateResponse
import com.example.projeto_barbearia.controllers.dtos.user.UserCreationResponse
import com.example.projeto_barbearia.controllers.dtos.user.UserUpdateRequest
import com.example.projeto_barbearia.data.models.Filial
import com.example.projeto_barbearia.data.repositories.filial_case.FilialRepository
import com.example.projeto_barbearia.utils.tools.TimeGatherer
import com.example.projeto_barbearia.utils.verifiers.BusinessCodeVerifier
import com.example.projeto_barbearia.utils.verifiers.EmailPatternVerifier
import com.example.projeto_barbearia.utils.verifiers.PasswordPatternVerifier
import com.example.projeto_barbearia.utils.verifiers.PatternVerifier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.security.cert.Certificate
import java.util.Optional
import java.util.UUID

@Service
class FilialServiceImpl(@Autowired private val repo: FilialRepository) : UserService{

    private lateinit var verifier: PatternVerifier

    override fun create(user: UserStrategy): UserCreationResponse? {
        println("Verifying type...")
        val taskResult0: Boolean = user is Filial

        println("Verifying email...")
        verifier = EmailPatternVerifier()
        val taskResult1: Boolean = verifier.verify(user.email)
        println("Verified email: $taskResult1")

        println("Verifying pass...")
        verifier = PasswordPatternVerifier()
        val taskResult2: Boolean = verifier.verify(user.pass)
        println("Verified pass: $taskResult2")

        println("Verifying Business Code...")
        verifier = BusinessCodeVerifier()
        val taskResult3: Boolean = verifier.verify(user.nationalCertificate!!)
        println("Verified code: $taskResult3")

        val answer: Boolean = taskResult0 && taskResult1 && taskResult2 //&& taskResult3



        return if(answer){
            val newFilial: Filial = repo.saveAndFlush(Filial(nationalCertificate = user.nationalCertificate, name = user.name, email = user.email, pass = user.pass))
            println("Created: $answer")
            UserCreationResponse(newFilial.name,answer,TimeGatherer.getDateAndTime())
        }else {
            null
        }
    }

    override fun getById(id: UUID): Filial? {
        val filial: Optional<Filial> = repo.findById(id)
        return if (filial.isPresent) filial.get() else throw ClassNotFoundException("Filial not found")
    }

    override fun getAll(): ArrayList<UserStrategy> {

        val list: List<Filial> =repo.findAll()
        println("Getting: ${list} | Size: ${list.size}")
        val resList: ArrayList<UserStrategy> = arrayListOf()

        list.forEach {
            resList.add(it)
        }

        return resList
    }

    override fun getByNationalCertificate(certificate: String): Filial? {

        var filial: Filial? = null
        repo.findAll().forEach {
            if(it.nationalCertificate == certificate) {
                filial = it
            }
        }

        return filial
    }

    override fun getByEmail(email: String): Filial? {

        var filial: Filial? = null
        repo.findAll().forEach {
            if(it.email == email) {
                filial = it
            }
        }

        return filial
    }

    override fun delete(user: UserStrategy): Int {

        var res: Int = 0
        try {
            val target: Filial = getByNationalCertificate(user.nationalCertificate!!).let {
                repo.findById(it!!.id!!).get()
            }

            repo.delete(target)
            res = 1
        } catch (e: IllegalArgumentException) {
            res = -1
        } catch (e: NoSuchElementException) {
            res = -1
        }

        return res
    }

    override fun update(id: UUID, data: UserUpdateRequest): Int {

        var res: Int = 0

        val target: Optional<Filial> = repo.findById(id)

        val filial: Filial = if (target.isPresent) target.get() else throw ClassNotFoundException("Filial not found")

        if(target.get().email != data.newEmail) {
            repo.saveAndFlush(
                Filial(
                    filial.id,
                    filial.nationalCertificate,
                    filial.name,
                    data.newEmail!!,
                    filial.pass
                )
            )
            res = 1
        }

        if(target.get().pass != data.newPass) {
            repo.saveAndFlush(
                Filial(
                    filial.id,
                    filial.nationalCertificate,
                    filial.name,
                    filial.email,
                    data.newPass!!
                )
            )
            res = 1
        }

        return res
    }
}