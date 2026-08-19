package com.example.projeto_barbearia.factory

import com.example.projeto_barbearia.config.contracts.UserStrategy
import com.example.projeto_barbearia.controllers.dtos.user.UserCreationRequest
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.data.models.Filial

class UserFactory() {

    fun make(user: UserCreationRequest) : UserStrategy {
        return when(user.type) {
            Cliente::class.simpleName -> {
                Cliente(name = user.name, email = user.email, pass = user.pass)
            }

            Filial::class.simpleName -> {
                Filial(name = user.name, email = user.email, pass = user.pass)
            }
            else -> throw IllegalArgumentException("User type not supported")
        }
    }
}