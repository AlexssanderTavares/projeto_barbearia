package com.example.projeto_barbearia.controllers

import com.example.projeto_barbearia.config.FilialTokenConfig
import com.example.projeto_barbearia.controllers.dtos.filial.requests.FilialCreateRequest
import com.example.projeto_barbearia.controllers.dtos.filial.response.FilialCreateResponse
import com.example.projeto_barbearia.controllers.dtos.login.LoginRequest
import com.example.projeto_barbearia.controllers.dtos.login.LoginResponse
import com.example.projeto_barbearia.data.models.Filial
import com.example.projeto_barbearia.services.implementations.FilialServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/business")
class FilialController(
    @Autowired private val service: FilialServiceImpl,
    @Autowired private val encoder: PasswordEncoder,
    @Autowired private val tokenConfig: FilialTokenConfig,
    private val authManager: AuthenticationManager
    ) {

    @PostMapping("/register")
    fun createBusiness(@RequestBody business: FilialCreateRequest) : ResponseEntity<FilialCreateResponse>{
        val newFilial: Filial = Filial(nationalCertificate = business.businessCode, name = business.name, email = business.email, pass = encoder.encode(business.pass)!!)
        val res: FilialCreateResponse?

        return if(service.getByEmail(newFilial.email) == null) {
            res = service.create(newFilial) ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
            return ResponseEntity.status(HttpStatus.CREATED).body(res)
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }
    }

    @PostMapping("/signin")
    fun businessLogin(@RequestBody dto: LoginRequest) : ResponseEntity<LoginResponse> {
        val authToken: UsernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(dto.email, dto.pass)
        val auth = authManager.authenticate(authToken)

        val token: String = tokenConfig.generateToken(auth.principal as Filial)
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(LoginResponse(token))
    }
}