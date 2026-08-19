package com.example.projeto_barbearia.controllers

import com.example.projeto_barbearia.config.TokenConfig
import com.example.projeto_barbearia.config.contracts.UserService
import com.example.projeto_barbearia.config.contracts.UserStrategy
import com.example.projeto_barbearia.controllers.dtos.login.LoginRequest
import com.example.projeto_barbearia.controllers.dtos.login.LoginResponse
import com.example.projeto_barbearia.controllers.dtos.user.UserCreationRequest
import com.example.projeto_barbearia.controllers.dtos.user.UserCreationResponse
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.data.models.Filial
import com.example.projeto_barbearia.services.abstracts.ClientService
import com.example.projeto_barbearia.services.abstracts.FilialService
import com.example.projeto_barbearia.services.implementations.ClientServiceImpl
import com.example.projeto_barbearia.services.implementations.FilialServiceImpl
import com.example.projeto_barbearia.utils.tools.TimeGatherer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    @Autowired private val encoder: PasswordEncoder,
    @Autowired private val clientService: ClientServiceImpl,
    @Autowired private val filialService: FilialServiceImpl,
    @Autowired private val tokenConfig: TokenConfig,
    private val authManager: AuthenticationManager
) {

@PostMapping("/new")
fun newUser(@RequestBody user: UserCreationRequest) : ResponseEntity<UserCreationResponse> {
    lateinit var target: UserStrategy
    lateinit var service: UserService

    when(user.type.lowercase()) {
        "cliente" -> {
            target = Cliente(name = user.name, email = user.email, pass = encoder.encode(user.pass)!!)
            service = clientService
        }

        "filial" -> {
            target = Filial(name = user.name, email = user.email, pass = encoder.encode(user.pass)!!)
            service = filialService
        }

        else -> {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null)
        }
    }

    val res: UserCreationResponse? = service.create(target)
    return if(res != null) ResponseEntity.status(HttpStatus.CREATED).body(UserCreationResponse(target.email, res.created,
        TimeGatherer.getDateAndTime())) else ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
}

    @GetMapping("/login")
    fun login(@RequestBody user: LoginRequest) : ResponseEntity<LoginResponse> {
        val authToken: UsernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(user.email, user.pass)
        val auth: Authentication = authManager.authenticate(authToken)

        val token: String = tokenConfig.generateToken(auth.principal as UserStrategy)
        return ResponseEntity.status(HttpStatus.OK).body(LoginResponse(token))

    }
}