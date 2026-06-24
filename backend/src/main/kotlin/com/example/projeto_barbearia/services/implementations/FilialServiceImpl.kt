package com.example.projeto_barbearia.services.implementations

import com.example.projeto_barbearia.data.dtos.filial.requests.FilialRequestDTO
import com.example.projeto_barbearia.data.models.Filial
import com.example.projeto_barbearia.data.models.views.FilialView
import com.example.projeto_barbearia.data.repositories.filial_case.FilialRepository
import com.example.projeto_barbearia.data.repositories.filial_case.FilialViewRepository
import com.example.projeto_barbearia.services.abstracts.FilialService
import com.example.projeto_barbearia.services.utils.verifiers.BusinessCodeVerifier
import com.example.projeto_barbearia.services.utils.verifiers.EmailPatternVerifier
import com.example.projeto_barbearia.services.utils.verifiers.PasswordPatternVerifier
import com.example.projeto_barbearia.services.utils.verifiers.PatternVerifier
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
class FilialServiceImpl(@Autowired val repo: FilialRepository, @Autowired val viewRepo: FilialViewRepository) : FilialService {

    private lateinit var verifier: PatternVerifier

    override fun create(filial: FilialRequestDTO): Int {
        var res: Int = 0

        println("Verifying email...")
        verifier = EmailPatternVerifier()
        val taskResult1: Boolean = verifier.verify(filial.email)
        println("Verified email: ${taskResult1}")

        println("Verifying pass...")
        verifier = PasswordPatternVerifier()
        val taskResult2: Boolean = verifier.verify(filial.pass)
        println("Verified pass: ${taskResult2}")

        println("Verifying Business Code...")
        verifier = BusinessCodeVerifier()
        val taskResult3: Boolean = verifier.verify(filial.cnpj)
        println("Verified code: ${taskResult3}")

        val answer: Boolean = taskResult1 && taskResult2 && taskResult3

        println("Created: ${answer}")

        return if(answer){
            repo.saveAndFlush(Filial(cnpj = filial.cnpj, name = filial.name, email = filial.email, pass = filial.pass!!, qtProf = filial.qtProf))
            res = 1
            res
        }else {
            res = -1
            res
        }
    }

    override fun getById(id: UUID): Filial? {
        val filial: Optional<Filial> = repo.findById(id)
        return if (filial.isPresent) filial.get() else throw ClassNotFoundException("Filial not found")
    }

    override fun getAll(): ArrayList<FilialView> {

        val list: List<FilialView> = viewRepo.findAll()
        println("Getting: ${list} | Size: ${list.size}")
        val resList: ArrayList<FilialView> = arrayListOf()

        list.forEach {
            resList.add(it)
        }

        return resList
    }

    override fun getByCnpj(cnpj: String): Filial? {

        var filial: Filial? = null
        repo.findAll().forEach {
            if(it.cnpj == cnpj) {
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

    override fun delete(filial: FilialRequestDTO): Int {

        var res: Int = 0
        try {
            val target: Filial = getByCnpj(filial.cnpj).let {
                repo.findById(it!!.id).get()
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

    override fun update(id: UUID, data: FilialRequestDTO): Int {

            var res: Int = 0

            val target: Optional<Filial> = repo.findById(id)

            if (target.isPresent) {

                val filial: Filial = target.get()

                when {
                    target.get().email != data.email -> {
                        repo.saveAndFlush(
                            Filial(
                                filial.id,
                                filial.cnpj,
                                filial.name,
                                data.email,
                                filial.pass,
                                filial.qtProf
                            )
                        )
                        res = 1
                    }

                    target.get().qtProf != data.qtProf -> {
                        repo.saveAndFlush(
                            Filial(
                                filial.id,
                                filial.cnpj,
                                filial.name,
                                filial.email,
                                filial.pass,
                                data.qtProf
                            )
                        )
                        res = 1
                    }

                    target.get().pass != data.pass -> {
                        repo.saveAndFlush(
                            Filial(
                                filial.id,
                                filial.cnpj,
                                filial.name,
                                data.email,
                                filial.pass,
                                filial.qtProf
                            )
                        )
                        res = 1
                    }
                }
            }

        return res
    }
}