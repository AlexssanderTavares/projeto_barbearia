package com.example.projeto_barbearia.controllers

import com.example.projeto_barbearia.config.ClientTokenConfig
import com.example.projeto_barbearia.controllers.dtos.cliente.requests.ClienteCreateRequestDTO
import com.example.projeto_barbearia.controllers.dtos.cliente.response.ClienteCreationResponseDTO
import com.example.projeto_barbearia.controllers.dtos.login.LoginRequest
import com.example.projeto_barbearia.controllers.dtos.login.LoginResponse
import com.example.projeto_barbearia.controllers.dtos.user.UserRequest
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.factory.UserFactory
import com.example.projeto_barbearia.services.implementations.ClientServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/client")
class ClientController(
    @Autowired private val service : ClientServiceImpl,
    @Autowired private val encoder : PasswordEncoder,
    @Autowired private val tokenConfig: ClientTokenConfig,
    private val authManager: AuthenticationManager
) {

    @PostMapping("/new")
    fun createClient(@RequestBody dto: ClienteCreateRequestDTO) : ResponseEntity<ClienteCreationResponseDTO> {
        val cliente: Cliente = Cliente(name = dto.name, email = dto.email, pass = encoder.encode(dto.pass)!!)
        val res: ClienteCreationResponseDTO? = service.create(cliente)
        return if (res != null) ResponseEntity.status(HttpStatus.CREATED).body(res) else ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
    }

    @PostMapping("/login")
    fun clientLogin(@RequestBody dto: LoginRequest) : ResponseEntity<LoginResponse>{
        val authToken: UsernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(dto.email, dto.pass)
        val auth = authManager.authenticate(authToken)

        val token: String = tokenConfig.generateToken(auth.principal as Cliente)
        return ResponseEntity.status(HttpStatus.OK).body(LoginResponse(token))
    }




}
