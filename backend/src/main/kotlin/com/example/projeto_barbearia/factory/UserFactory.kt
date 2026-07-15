package com.example.projeto_barbearia.factory

import com.example.projeto_barbearia.config.contracts.UserStrategy
import com.example.projeto_barbearia.controllers.dtos.user.UserRequest
import com.example.projeto_barbearia.data.models.Cliente
import com.example.projeto_barbearia.data.models.Filial
import kotlin.jvm.java

class UserFactory(val request: UserRequest) {

    fun make() : UserStrategy {
        return when {
            request.userType == Cliente::class.java.simpleName.lowercase() -> Cliente(id = request.user.id, name = request.user.name, nationalCertificate = request.user.nationalCertificate,pass = request.user.pass, email = request.user.email)
            request.userType == Filial::class.java.simpleName.lowercase() -> Filial(id = request.user.id, name = request.user.name, email = request.user.email, pass = request.user.pass)
            else -> throw NoSuchElementException("Invalid UserType")
        }
    }
}