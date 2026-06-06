package com.example.projeto_barbearia.integration.services

import com.example.projeto_barbearia.config.TestcontainersConfiguration
import com.example.projeto_barbearia.data.dtos.cliente.ClienteRequestDTO
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.data.models.views.Cliente_View
import com.example.projeto_barbearia.data.repositories.cliente_case.ClienteRepository
import com.example.projeto_barbearia.data.repositories.cliente_case.ClienteViewRepo
import com.example.projeto_barbearia.services.implementations.ClientServiceImpl
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.postgresql.PostgreSQLContainer
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

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

    private lateinit var dummy: ClienteRequestDTO

    @BeforeTest
    suspend fun setup() {
        runBlocking {
            dummy = ClienteRequestDTO("Dummy", "dummy@test.com", "#dummy12")
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
                assertEquals(1, clientService.create(ClienteRequestDTO(cliente.name, cliente.email, cliente.pass)))
            } catch (e: Exception) {
                e.printStackTrace()
                fail(e.message)
            }

    }

    @Test
    suspend fun tryCreateClientUsingCreationDTOAndReturnSuccess() {
            try {
                assertEquals(1, clientService.create(dummy))
            } catch (e: Exception) {
                e.printStackTrace()
                fail(e.message)
            }
    }

    @Test
    suspend fun tryCreateClientUsingFakeClientInstanceAndReturnFailure() {
        val dto1: ClienteRequestDTO = ClienteRequestDTO("Dummy1", "dummy1@teste.com", "12dummy")
        val dto2: ClienteRequestDTO = ClienteRequestDTO( "Dummy2", "dummy2teste.com", "@12dummy")
        val dto3: ClienteRequestDTO = ClienteRequestDTO( "Dummy3", "dummy3teste.com", "12dummy")

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
        val dto1: ClienteRequestDTO = ClienteRequestDTO("Dummy1", "dummy1@teste.com", "#13dummy")
        val dto2: ClienteRequestDTO = ClienteRequestDTO("Dummy2", "dummy2@teste.com", "@12dummy")
        val dto3: ClienteRequestDTO = ClienteRequestDTO("Dummy3", "dummy3@teste.com", "$12dummy")

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
        var res: Int = clientService.create(dummy)
        println("Dummy registered in database with result code: $res")

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

    @Test
    suspend fun tryDeleteClienteUsingRequestDTOAndReturnSuccess() {
        try{
            println("Creating dummy... Result code: ${clientService.create(dummy)}")
            println("Now trying to delete it...")
            assertEquals(1, clientService.delete(dummy))
            assertEquals(null, clientService.getByEmail(dummy.email))
        }catch (e: Exception) {
            e.printStackTrace()
            fail(e.message)
        }
    }

    @Test
    suspend fun tryUpdateClienteEmailByUsingRequestDTOAndReturnSuccess() {
        try{
            println("Creating dummy... result code: ${clientService.create(dummy)}")
            var res: Int = clientService.update(clientService.getByEmail(dummy.email)!!.id_cliente, ClienteRequestDTO(dummy.name, "dummy_updated@test.com", dummy.pass))

            println("Trying to update email...Result: ${res}")
            assertEquals(1, res , message = "Email updated..." )

            val target: Cliente_View? = clientService.getByEmail("dummy_updated@test.com")
            assertNotEquals(null, target, "Updated data: ${target}" )

        } catch (e: Exception) {
            e.printStackTrace()
            fail(e.message)
        }
    }

    @Test
    suspend fun tryUpdateClienteNameByUsingRequestDTOAndReturnSuccess() {
        try{
            println("Creating dummy... result code: ${clientService.create(dummy)}")
            val res: Int = clientService.update(clientService.getByEmail(dummy.email)!!.id_cliente, ClienteRequestDTO("Updated_Dummy", dummy.email, dummy.pass))

            println("Trying to update name...Result: ${res}")
            assertEquals(1, res, message = "Name updated..." )

            val target: Cliente_View? = clientService.getByEmail(dummy.email)
            assertNotEquals(dummy.name, target!!.nome, "Updated data: ${target}")

        } catch (e: Exception) {
            e.printStackTrace()
            fail(e.message)
        }
    }

    @Test
    suspend fun tryUpdateClientePasswordByUsingRequestDTOAndReturnSuccess() {
        try{
            println("Creating dummy... result code: ${clientService.create(dummy)}")
            val res: Int = clientService.update(clientService.getByEmail(dummy.email)!!.id_cliente, ClienteRequestDTO(dummy.name, dummy.email, "#Pass456"))

            println("Trying to update name...Result: ${res}")
            assertEquals(1, res, message = "Password updated..." )

        } catch (e: Exception) {
            e.printStackTrace()
            fail(e.message)
        }
    }

}