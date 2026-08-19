package com.example.projeto_barbearia.controllers

import com.example.projeto_barbearia.config.contracts.UserStrategy
import com.example.projeto_barbearia.controllers.dtos.cliente.requests.ClienteCreateRequest
import com.example.projeto_barbearia.controllers.dtos.cliente.requests.ClienteGetRequest
import com.example.projeto_barbearia.controllers.dtos.cliente.requests.ClienteUpdateWrapper
import com.example.projeto_barbearia.controllers.dtos.cliente.response.ClienteCreationResponse
import com.example.projeto_barbearia.controllers.dtos.cliente.response.ClienteDeleteResponse
import com.example.projeto_barbearia.controllers.dtos.cliente.response.ClienteGetResponse
import com.example.projeto_barbearia.controllers.dtos.cliente.response.ClienteUpdateResponse
import com.example.projeto_barbearia.controllers.dtos.login.LoginRequest
import com.example.projeto_barbearia.controllers.dtos.login.LoginResponse
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.services.implementations.ClientServiceImpl
import com.example.projeto_barbearia.utils.tools.TimeGatherer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/client")
class ClientController(
    @Autowired private val service : ClientServiceImpl,
    @Autowired private val encoder : PasswordEncoder,
    //@Autowired private val tokenConfig: ClientTokenConfig,
    private val authManager: AuthenticationManager
) {
/*
    @PostMapping("/new")
    fun createClient(@RequestBody dto: ClienteCreateRequest): ResponseEntity<ClienteCreationResponse> {
        val cliente: UserStrategy = Cliente(name = dto.name, email = dto.email, pass = encoder.encode(dto.pass)!!)
        val res: ClienteCreationResponse? = service.create(cliente)
        return if (res != null) ResponseEntity.status(HttpStatus.CREATED).body(res) else ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
    }

    @PostMapping("/login")
    fun clientLogin(@RequestBody dto: LoginRequest): ResponseEntity<LoginResponse> {
        val authToken: UsernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(dto.email, dto.pass)
        val auth = authManager.authenticate(authToken)

        val token: String = tokenConfig.generateToken(auth.principal as Cliente)
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(LoginResponse(token))
    }

    @GetMapping("/list")
    fun getAll(): ResponseEntity<ArrayList<Cliente>> {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll())
    }

    @GetMapping("/get/email")
    fun getByEmail(@RequestBody cliente: ClienteGetRequest): ResponseEntity<ClienteGetResponse> {
        if (cliente.email != null) {
            val user: Cliente = service.getByEmail(cliente.email) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
            return ResponseEntity.status(HttpStatus.OK).body(ClienteGetResponse(user.name, user.email, user.nationalCertificate))
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }
    }

    @PatchMapping("/update")
    fun updateClient(@RequestBody wrapper: ClienteUpdateWrapper): ResponseEntity<ClienteUpdateResponse> {
        val user: Cliente = service.getByEmail(wrapper.getRequest.email!!) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        return ResponseEntity.status(HttpStatus.OK).body(ClienteUpdateResponse(service.update(user.id!!, wrapper.updateRequest) != 0,TimeGatherer.getDateAndTime())) //must fix here
    }

    @DeleteMapping("/delete")
    fun deleteClient(@RequestBody cliente: ClienteGetRequest) : ResponseEntity<ClienteDeleteResponse> {
        val user: Cliente = service.getByEmail(cliente.email!!) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ClienteDeleteResponse(false, TimeGatherer.getDateAndTime()))
        val res: ClienteDeleteResponse = ClienteDeleteResponse(service.delete(user) != 0, TimeGatherer.getDateAndTime())
        return ResponseEntity.status(HttpStatus.OK).body(res)
    }*/
}