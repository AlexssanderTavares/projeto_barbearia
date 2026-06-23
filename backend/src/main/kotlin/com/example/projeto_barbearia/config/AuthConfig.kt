package com.example.projeto_barbearia.config

import com.example.projeto_barbearia.config.contracts.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthConfig(@Autowired private val dataProvider: UserService) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        TODO("Not yet implemented")
    }
}