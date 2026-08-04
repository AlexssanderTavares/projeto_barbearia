package com.example.projeto_barbearia.services.implementations

import com.example.projeto_barbearia.controllers.dtos.filial.requests.FilialRequestDTO
import com.example.projeto_barbearia.controllers.dtos.filial.response.FilialCreateResponse
import com.example.projeto_barbearia.data.models.Filial
import com.example.projeto_barbearia.data.repositories.filial_case.FilialRepository
import com.example.projeto_barbearia.utils.tools.TimeGatherer
import com.example.projeto_barbearia.utils.verifiers.BusinessCodeVerifier
import com.example.projeto_barbearia.utils.verifiers.EmailPatternVerifier
import com.example.projeto_barbearia.utils.verifiers.PasswordPatternVerifier
import com.example.projeto_barbearia.utils.verifiers.PatternVerifier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
class FilialServiceImpl(@Autowired val repo: FilialRepository){

    private lateinit var verifier: PatternVerifier

    fun create(filial: Filial): FilialCreateResponse? {

        println("Verifying email...")
        verifier = EmailPatternVerifier()
        val taskResult1: Boolean = verifier.verify(filial.email)
        println("Verified email: $taskResult1")

        println("Verifying pass...")
        verifier = PasswordPatternVerifier()
        val taskResult2: Boolean = verifier.verify(filial.pass)
        println("Verified pass: $taskResult2")

        println("Verifying Business Code...")
        verifier = BusinessCodeVerifier()
        val taskResult3: Boolean = verifier.verify(filial.nationalCertificate!!)
        println("Verified code: $taskResult3")

        val answer: Boolean = taskResult1 && taskResult2 && taskResult3

        println("Created: $answer")

        return if(answer){
            val newFilial: Filial = repo.saveAndFlush(Filial(nationalCertificate = filial.nationalCertificate, name = filial.name, email = filial.email, pass = filial.pass))
            FilialCreateResponse(newFilial.name, newFilial.email, answer, TimeGatherer.getDateAndTime())
        }else {
            null
        }
    }

    fun getById(id: UUID): Filial? {
        val filial: Optional<Filial> = repo.findById(id)
        return if (filial.isPresent) filial.get() else throw ClassNotFoundException("Filial not found")
    }

    fun getAll(): ArrayList<Filial> {

        val list: List<Filial> =repo.findAll()
        println("Getting: ${list} | Size: ${list.size}")
        val resList: ArrayList<Filial> = arrayListOf()

        list.forEach {
            resList.add(it)
        }

        return resList
    }

    fun getByCnpj(cnpj: String): Filial? {

        var filial: Filial? = null
        repo.findAll().forEach {
            if(it.nationalCertificate == cnpj) {
                filial = it
            }
        }

        return filial
    }

    fun getByEmail(email: String): Filial? {

        var filial: Filial? = null
        repo.findAll().forEach {
            if(it.email == email) {
                filial = it
            }
        }

        return filial
    }

    fun delete(filial: FilialRequestDTO): Int {

        var res: Int = 0
        try {
            val target: Filial = getByCnpj(filial.cnpj!!).let {
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

    fun update(id: UUID, data: FilialRequestDTO): Int {

            var res: Int = 0

            val target: Optional<Filial> = repo.findById(id)

            if (target.isPresent) {

                val filial: Filial = target.get()

                when {
                    target.get().email != data.email -> {
                        repo.saveAndFlush(
                            Filial(
                                filial.id,
                                filial.nationalCertificate,
                                filial.name,
                                data.email,
                                filial.pass
                            )
                        )
                        res = 1
                    }

                    target.get().pass != data.pass -> {
                        repo.saveAndFlush(
                            Filial(
                                filial.id,
                                filial.nationalCertificate,
                                filial.name,
                                data.email,
                                filial.pass
                            )
                        )
                        res = 1
                    }
                }
            }

        return res
    }
}