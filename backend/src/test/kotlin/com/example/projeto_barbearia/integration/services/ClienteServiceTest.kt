package com.example.projeto_barbearia.integration.services

import com.example.projeto_barbearia.config.TestcontainersConfiguration
import com.example.projeto_barbearia.data.DTOs.Cliente.ClienteCreationDTO
import com.project.barbearia.data.models.Cliente
import com.project.barbearia.data.models.views.ClientView
import com.project.barbearia.data.repositories.ClienteRepository
import com.project.barbearia.data.repositories.ClienteViewRepository
import com.project.barbearia.services.implementations.ClientServiceImpl
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.postgresql.PostgreSQLContainer
import java.util.Optional
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.asserter

@Import(TestcontainersConfiguration::class)
@Testcontainers
@SpringBootTest
class ClienteServiceTest {

    lateinit var clientService: ClientServiceImpl

    @Autowired
    lateinit var container: PostgreSQLContainer

    @BeforeTest
    fun setup() {
        container.start()
        val mockRepo: ClienteRepository = Mockito.mock(ClienteRepository::class.java)
        val viewRepo: ClienteViewRepository = Mockito.mock(ClienteViewRepository::class.java)
        clientService = ClientServiceImpl(mockRepo, viewRepo)
    }

    @AfterTest
    fun teardown() {
        container.stop()
    }

    @Test
    fun testIfContainerIsRunning() {
        val res = container.containerInfo
        assertEquals(true, res.state.running)
    }

    @Test
    suspend fun tryCreateClientUsingFakeClientInstanceAndReturnSuccess() {
            val cliente: Cliente = Cliente(null, "TesteDummy", "dummy@test.com", "@anypass12345", "12345-678")
            try {
                assertEquals(1, clientService.create(ClienteCreationDTO(cliente.name, cliente.email, cliente.pass)))
            } catch (e: Exception) {
                e.printStackTrace()
                fail(e.message)
            }
    }

    @Test
    suspend fun tryCreateClientUsingCreationDTOAndReturnSuccess() {
        val dto: ClienteCreationDTO = ClienteCreationDTO("Dummy", "dummy@test.com", "#123dummy")
        val cliente: Cliente = Cliente(null, dto.name, dto.email, dto.pass, null)
        try {
            assertEquals(1, clientService.create(dto))
        } catch (e: Exception) {
            e.printStackTrace()
            fail(e.message)
        }
    }

    @Test
    suspend fun tryCreateClientUsingFakeClientInstanceAndReturnFailure() {
        val dto1: ClienteCreationDTO = ClienteCreationDTO("Dummy1", "dummy1@teste.com", "123dummy")
        val dto2: ClienteCreationDTO = ClienteCreationDTO("Dummy2", "dummy2teste.com", "@123dummy")
        val dto3: ClienteCreationDTO = ClienteCreationDTO("Dummy3", "dummy3teste.com", "123dummy")

        try{
            assertEquals(-1, clientService.create(dto1))
            assertEquals(-1, clientService.create(dto2))
            assertEquals(-1, clientService.create(dto3))
        } catch (e: Exception) {
            e.printStackTrace()
            fail(e.message)
        }
    }

    @Test
    suspend fun tryGetClienteUsingDTOandReturnAnExistingClientInDatabase() {
        val dto: ClienteCreationDTO = ClienteCreationDTO("Dummy", "dummy@test.com", "#123dummy")

        try {
            var creationRes: Int = clientService.create(dto)
            println("Target created: $creationRes")
            val target: ClientView = clientService.getByEmail(dto.email).get()
            println(target)


        }catch (e: Exception) {
            e.printStackTrace()
            fail(e.message)
        }


    }

}