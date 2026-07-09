package com.example.projeto_barbearia.config.contracts

import com.example.projeto_barbearia.controllers.dtos.user.UserCreationRequest
import com.example.projeto_barbearia.controllers.dtos.user.UserCreationResponse
import java.util.UUID

interface UserService {

    fun create(user: UserCreationRequest): UserCreationResponse
    fun getByEmail(email: String) : UserStrategy?
    fun getById(id : UUID) : UserStrategy?
    fun getAll() : List<UserStrategy>
}