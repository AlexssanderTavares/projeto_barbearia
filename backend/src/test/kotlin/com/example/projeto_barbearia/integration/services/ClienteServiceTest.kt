package com.example.projeto_barbearia.integration.services

import com.example.projeto_barbearia.config.TestcontainersConfiguration
import com.example.projeto_barbearia.data.DTOs.Cliente.ClienteCreationDTO
import com.project.barbearia.data.models.Cliente
import com.project.barbearia.data.repositories.ClienteRepository
import com.project.barbearia.data.repositories.ClienteViewRepository
import com.project.barbearia.services.implementations.ClientServiceImpl
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.postgresql.PostgreSQLContainer
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

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
    fun tryCreateClientUsingFakeClientInstanceAndReturnSuccess() {
        val cliente: Cliente = Cliente(null, "TesteDummy", "dummy@test.com", "anypass12345", "12345-678")
        assertEquals(1, clientService.create(cliente))
    }

    @Test
    fun tryCreateClientUsingCreationDTOAndReturnSuccess(){
        val dto: ClienteCreationDTO = ClienteCreationDTO("Dummy", "dummy@test.com", "123dummy")
        val cliente: Cliente = Cliente(null, dto.name, dto.email, dto.pass, null)
        assertEquals(1, clientService.create(cliente))
    }

}