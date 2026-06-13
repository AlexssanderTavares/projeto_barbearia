package com.example.projeto_barbearia.services.implementations

import com.example.projeto_barbearia.data.dtos.filial.FilialRequestDTO
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
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
class FilialServiceImpl(@Autowired val repo: FilialRepository, @Autowired val viewRepo: FilialViewRepository) : FilialService {

    private lateinit var verifier: PatternVerifier

    override suspend fun create(filial: FilialRequestDTO): Int {
        var res: Int = 0

        val createTask: Deferred<Int> = CoroutineScope(Dispatchers.IO).async {

            val verifyEmailTask: Deferred<Boolean> = async {
                println("Verifying email...")
                verifier = EmailPatternVerifier()
                val taskResult: Boolean = verifier.verify(filial.email)
                println("Verified email: ${taskResult}")
                taskResult
            }

            val verifyPassTask: Deferred<Boolean> = async {
                println("Verifying pass...")
                verifier = PasswordPatternVerifier()
                val taskResult: Boolean = verifier.verify(filial.pass)
                println("Verified pass: ${taskResult}")
                taskResult
            }

            val verifyBusinessCodeTask: Deferred<Boolean> = async {
                println("Verifying Business Code...")
                verifier = BusinessCodeVerifier()
                val taskResult: Boolean = verifier.verify(filial.cnpj)
                println("Verified code: ${taskResult}")
                taskResult
            }

            val answer: Boolean = verifyEmailTask.await() && verifyPassTask.await() && verifyBusinessCodeTask.await()

            println("Created: ${answer}")
            delay(2000)

            if(answer){
                repo.saveAndFlush(Filial(cnpj = filial.cnpj, name = filial.name, email = filial.email, pass = filial.pass, qt_prof = filial.qtProf))
                res = 1
                res
            }else {
                res = -1
                res
            }
        }

        return createTask.await()
    }

    override suspend fun getById(id: UUID): Filial? {
        val filial: Optional<Filial> = repo.findById(id)
        return if (filial.isPresent) filial.get() else throw ClassNotFoundException("Filial not found")
    }

    override suspend fun getAll(): ArrayList<FilialView> {
        val getAllFilialTask: Deferred<ArrayList<FilialView>> = CoroutineScope(Dispatchers.IO).async {
            val list: List<FilialView> = viewRepo.findAll()
            println("Getting: ${list} | Size: ${list.size}")
            val resList: ArrayList<FilialView> = arrayListOf()

            list.forEach {
                resList.add(it)
            }

            resList
        }

        return getAllFilialTask.await()
    }

    override suspend fun getByCnpj(cnpj: String): FilialView? {
        val tryGetByBusinessCodeTaks: Deferred<FilialView?> = CoroutineScope(Dispatchers.IO).async {
            var filial: FilialView? = null
            viewRepo.findAll().forEach {
                if(it.cnpj == cnpj) {
                    filial = it
                }
            }

            filial
        }

        return tryGetByBusinessCodeTaks.await()
    }

    override suspend fun getByEmail(email: String): FilialView? {
        val tryGetFilialByEmailTask: Deferred<FilialView?> = CoroutineScope(Dispatchers.IO).async {
            var filial: FilialView? = null
            viewRepo.findAll().forEach {
                if(it.email == email) {
                    filial = it
                }
            }
            filial
        }

        return tryGetFilialByEmailTask.await()
    }

    override suspend fun delete(filial: FilialRequestDTO): Int {
        val deleteFilialTask: Deferred<Int> = CoroutineScope(Dispatchers.IO).async {
            var res: Int = 0
            try {
                val target: Filial = getByCnpj(filial.cnpj).let {
                    repo.findById(it!!.id).get()
                }

                repo.delete(target)
                res = 1
            } catch (e: IllegalArgumentException) {
                res = -1
                throw CancellationException("Argumento inválido passado na chamada do método delete")
            } catch (e: NoSuchElementException) {
                res = -1
                throw CancellationException("Elemento não encontrado ou não existe")

            }
             res
        }

        return deleteFilialTask.await()
    }

    override suspend fun update(filial: UUID, data: FilialRequestDTO): Int {
        TODO("Not yet implemented")
    }
}