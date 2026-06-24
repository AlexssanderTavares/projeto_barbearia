package com.example.projeto_barbearia.config

import com.example.projeto_barbearia.config.contracts.UserService
import com.example.projeto_barbearia.config.contracts.UserStrategy
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.data.models.Filial
import com.example.projeto_barbearia.services.implementations.ClientServiceImpl
import com.example.projeto_barbearia.services.implementations.FilialServiceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthConfig(@Autowired private val dataProvider: UserService) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val res: UserDetails
            when(dataProvider) {
                is ClientServiceImpl -> {
                    res = dataProvider.getByEmail(username) as Cliente
                }
                is FilialServiceImpl -> {
                    res = dataProvider.getByEmail(username) as Filial
                }
                else -> {
                    throw UsernameNotFoundException("Username $username not found")
                }
            }

        return res
    }
}