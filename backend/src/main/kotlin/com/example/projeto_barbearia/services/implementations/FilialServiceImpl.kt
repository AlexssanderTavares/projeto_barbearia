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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
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
        TODO("Not yet implemented")
    }

    override suspend fun getByCnpj(cnpj: String): FilialView? {
        TODO("Not yet implemented")
    }

    override suspend fun getByEmail(email: String): FilialView? {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): ArrayList<FilialView> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(filial: Filial): Int {
        TODO("Not yet implemented")
    }

    override suspend fun update(filial: UUID, data: FilialRequestDTO): Int {
        TODO("Not yet implemented")
    }
}