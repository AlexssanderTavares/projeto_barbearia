package com.example.projeto_barbearia.integration.services

import com.example.projeto_barbearia.config.TestcontainersConfiguration
import com.example.projeto_barbearia.data.dtos.filial.FilialRequestDTO
import com.example.projeto_barbearia.data.models.Filial
import com.example.projeto_barbearia.data.repositories.filial_case.FilialRepository
import com.example.projeto_barbearia.data.repositories.filial_case.FilialViewRepository
import com.example.projeto_barbearia.services.abstracts.FilialService
import com.example.projeto_barbearia.services.implementations.FilialServiceImpl
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.postgresql.PostgreSQLContainer
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.junit5.JUnit5Asserter.fail

@Import(TestcontainersConfiguration::class)
@Testcontainers
@SpringBootTest
class FilialServiceTest {

    private lateinit var filialService: FilialService
    private lateinit var dummy: FilialRequestDTO

    @Autowired
    private lateinit var container: PostgreSQLContainer

    @Autowired
    private lateinit var repo: FilialRepository

    @Autowired
    private lateinit var viewRepo: FilialViewRepository

    @BeforeTest
    fun setup() {
        dummy = FilialRequestDTO("22.111.333/0002-66", "DummyBarber", "dummy@test.com", "#Dummy12", 1)
        container.start()
        filialService = FilialServiceImpl(repo, viewRepo)
    }

    @AfterTest
    fun teardown() {
        container.stop()
    }

    @Test
    fun testIfContainerIsRunning() {
        var res = container.containerInfo
        assertEquals(true, res.state.running)
    }

    @Test
    suspend fun tryCreateFilialUsingRequestDTOAndReturnSuccess() {
        try{
            assertEquals(1, filialService.create(dummy))
        } catch(e: Exception){
            e.printStackTrace()
            fail(e.message)
        }
    }

    @Test
    suspend fun tryCreateFilialUsingFakeRequestDTOAndReturnFailure() {
        val dummy1: FilialRequestDTO = FilialRequestDTO("44655888/0004-88", "Dummy1", "dummy1@test.com", "#Dummy12", 1)
        val dummy2: FilialRequestDTO = FilialRequestDTO("44.655.888/0004-88", "Dummy2", "dummy2test.com", "#Dummy12", 1)
        val dummy3: FilialRequestDTO = FilialRequestDTO("44.655.999/0004-88", "Dummy3", "dummy3@test.com", "#Dummy1223", 1)

        try{
            assertNotEquals(1, filialService.create(dummy1))
            assertNotEquals(1, filialService.create(dummy2))
            assertNotEquals(1, filialService.create(dummy3))
        } catch (e: Exception){
            e.printStackTrace()
            fail(e.message)
        }

    }

}