package com.project.barbearia.services.implementations

import com.project.barbearia.data.models.OrdemServico
import com.project.barbearia.data.models.views.OSView
import com.project.barbearia.data.repositories.OrdemServicoRepository
import com.project.barbearia.data.repositories.OrdemServicoViewRepository
import com.project.barbearia.services.abstracts.ClientService
import com.project.barbearia.services.abstracts.ProfissionalService
import com.sun.org.apache.xalan.internal.lib.ExsltDatetime.year
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit.Companion.YEAR
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cglib.core.Local
import java.sql.Timestamp
import java.time.DateTimeException
import java.time.LocalDateTime
import java.util.Optional
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.absoluteValue

class OrdemServicoServiceImpl(
    @Autowired private val repo: OrdemServicoRepository,
    @Autowired private val viewRepo: OrdemServicoViewRepository,
    private val clService: ClientService,
    private val profService: ProfissionalService
) {

    fun create(os: OrdemServico): Int {
        var res: Int = 0

        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            try {
                if (!clService.getById(os.idClient).isEmpty && !profService.getById(os.idProf).isEmpty) {
                    repo.save(os)
                    repo.flush()
                    res = 1
                } else {
                    throw NoSuchElementException()
                }
            } catch (e: NoSuchElementException) {
                res = -1
                if (clService.getById(os.idClient).isEmpty) {
                    coroutineContext.cancel()
                }
            } catch (e: NoSuchElementException) {
                res = -1
                if (profService.getById(os.idProf).isEmpty) {
                    coroutineContext.cancel()
                }
            }
        }

        scope.start()
        return when {
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }
    }

    fun getById(id: Long): Optional<OSView> {
        return viewRepo.findById(id)
    }

    fun getAll(): List<OSView> {
        return viewRepo.findAll()
    }

    fun delete(os: OrdemServico): Int {
        var res: Int = 0

        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            try {
                if (!getById(os.os).isEmpty) {
                    repo.delete(os)
                    repo.flush()
                    res = 1
                }
            } catch (e: NoSuchElementException) {
                res = -1
                coroutineContext.cancel()
            }
        }

        scope.start()
        return when {
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }
    }

    fun update(os: OrdemServico, newDate: LocalDateTime): Int {
        var res: Int = 0

        val scope: Job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val res1: Deferred<Boolean> = async(Dispatchers.Unconfined) {
                    if (os.date.year != newDate.year) {
                        os.date.withYear(newDate.year)
                        true
                    } else {
                        false
                    }
                }
                val res2: Deferred<Boolean> = async(Dispatchers.Unconfined) {
                    if (os.date.month != newDate.month){
                        os.date.withMonth(newDate.month.value)
                        true
                    } else {
                        false
                    }
                }
                val res3: Deferred<Boolean> = async(Dispatchers.Unconfined) {
                    if (os.date.dayOfMonth != newDate.dayOfMonth) {
                        os.date.withDayOfMonth(newDate.dayOfMonth)
                        true
                    } else {
                        false
                    }
                }
                val res4: Deferred<Boolean> = async(Dispatchers.Unconfined) {
                    if (os.date.hour != newDate.hour) {
                        os.date.withHour(newDate.hour)
                        true
                    } else {
                        false
                    }
                }
                val res5: Deferred<Boolean> = async (Dispatchers.Unconfined) {
                    if (os.date.minute != newDate.minute) {
                        os.date.withMinute(newDate.minute)
                        true
                    } else {
                        false
                    }
                }

                if (res1.await() || res2. await() || res3.await() || res4.await() || res5.await()){
                    repo.save(os)
                    repo.flush()
                    res = 1
                }

            } catch (e: DateTimeException) {
                res = -1
                coroutineContext.cancel()
            } catch (e: NoSuchElementException) {
                res = -1
                coroutineContext.cancel()
            } catch (e: IllegalArgumentException) {
                res = -1
                coroutineContext.cancel()
            }
        }

        scope.start()
        return when{
            scope.isCompleted -> res
            scope.isCancelled -> res
            else -> 0
        }

    }
}