package com.example.projeto_barbearia.config

import com.example.projeto_barbearia.data.repositories.filial_case.FilialRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class BusinessAuthConfig(@Autowired private val repo: FilialRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return repo.findByEmail(username).orElseThrow { UsernameNotFoundException("Username/email: $username not found") }
    }
}