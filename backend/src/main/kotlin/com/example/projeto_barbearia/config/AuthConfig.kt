package com.example.projeto_barbearia.config

import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.data.models.Filial
import com.example.projeto_barbearia.data.repositories.cliente_case.ClienteRepository
import com.example.projeto_barbearia.data.repositories.filial_case.FilialRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthConfig(@Autowired private val clientRepo: ClienteRepository, @Autowired private val busiRepo: FilialRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val client: Cliente = clientRepo.findByEmail(username).get()
        val business: Filial = busiRepo.findByEmail(username).get()

        return when {
            !clientRepo.findByEmail(username).isEmpty -> {
                clientRepo.findByEmail(username).get()
            }

            !busiRepo.findByEmail(username).isEmpty -> {
                busiRepo.findByEmail(username).get()
            }

            else -> {
                throw UsernameNotFoundException("Email not yet registered!")
            }
        }
    }
}

/***
 * Tentar mesclar as funções create e login num unico controller com uma abstração de usuário para as models Cliente e Filial
 */