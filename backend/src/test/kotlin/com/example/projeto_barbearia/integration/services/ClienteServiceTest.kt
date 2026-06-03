package com.example.projeto_barbearia.integration.services

import com.example.projeto_barbearia.config.TestcontainersConfiguration
import com.example.projeto_barbearia.data.dtos.cliente.ClienteCreationDTO
import com.example.projeto_barbearia.data.dtos.cliente.ClienteView
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.data.models.views.Cliente_View
import com.example.projeto_barbearia.data.repositories.cliente_case.ClienteRepository
import com.example.projeto_barbearia.data.repositories.cliente_case.ClienteViewRepo
import com.example.projeto_barbearia.services.abstracts.ClientService
import com.example.projeto_barbearia.services.implementations.ClientServiceImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.fail
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

    @Autowired
    lateinit var clienteRepository: ClienteRepository

    @Autowired
    lateinit var clienteViews: ClienteViewRepo

    @BeforeTest
    suspend fun setup() {
        runBlocking {
            container.start()
            clientService = ClientServiceImpl(clienteRepository, clienteViews)
        }
    }

    @AfterTest
    suspend fun teardown() {
        container.stop()
    }

    @Test
    fun testIfContainerIsRunning() {
        val res = container.containerInfo
        assertEquals(true, res.state.running)
    }

    @Test
    suspend fun tryCreateClientUsingFakeClientInstanceAndReturnSuccess() {
            val cliente: Cliente = Cliente(id = null, name = "TesteDummy", email = "dummy@test.com", pass ="@pass123")
            try {
                assertEquals(1, clientService.create(ClienteCreationDTO(cliente.name, cliente.email, cliente.pass)))
            } catch (e: Exception) {
                e.printStackTrace()
                fail(e.message)
            }

    }

    @Test
    suspend fun tryCreateClientUsingCreationDTOAndReturnSuccess() {
            val dto: ClienteCreationDTO = ClienteCreationDTO("Dummy", "dummy@test.com", "#12dummy")
            try {
                assertEquals(1, clientService.create(dto))
            } catch (e: Exception) {
                e.printStackTrace()
                fail(e.message)
            }
    }

    @Test
    suspend fun tryCreateClientUsingFakeClientInstanceAndReturnFailure() {
        val dto1: ClienteCreationDTO = ClienteCreationDTO("Dummy1", "dummy1@teste.com", "12dummy")
        val dto2: ClienteCreationDTO = ClienteCreationDTO( "Dummy2", "dummy2teste.com", "@12dummy")
        val dto3: ClienteCreationDTO = ClienteCreationDTO( "Dummy3", "dummy3teste.com", "12dummy")

        try {
            assertEquals(-1, clientService.create(dto1))
            assertEquals(-1, clientService.create(dto2))
            assertEquals(-1, clientService.create(dto3))
        } catch (e: Exception) {
            e.printStackTrace()
            fail(e.message)
        }
    }

    @Test
    suspend fun tryGetAllRegistersAsAListOfViews(){
        println("Creating subjects...")
        val dto1: ClienteCreationDTO = ClienteCreationDTO("Dummy1", "dummy1@teste.com", "#13dummy")
        val dto2: ClienteCreationDTO = ClienteCreationDTO("Dummy2", "dummy2@teste.com", "@12dummy")
        val dto3: ClienteCreationDTO = ClienteCreationDTO("Dummy3", "dummy3@teste.com", "$12dummy")

        clientService.create(dto1)
        clientService.create(dto2)
        clientService.create(dto3)

        println(clientService.getAll())
        try {
            if(clientService.getAll().isNotEmpty()) {
                clientService.getAll().forEach {
                    println(it.toString())
                }
            }else{
                fail("No clientes found")
            }
        }catch (e: Exception) {
            println(e.message)
            e.printStackTrace()
        }

    }

    @Test
    suspend fun tryGetClienteUsingDTOandReturnAnExistingClientInDatabase() {
        var target: Cliente_View? = null
        var created: Int = clientService.create(ClienteCreationDTO("Dummy", "dummy@test.com", "#dummy12"))
        println("Dummy registered in database with result code: $created")

            try {
                target = clientService.getByEmail("dummy@test.com")!!

            } catch (e: Exception) {
                e.printStackTrace()
                println(e.message)
            } finally {
                println(target)
            }
        assertNotNull(target)
    }

}