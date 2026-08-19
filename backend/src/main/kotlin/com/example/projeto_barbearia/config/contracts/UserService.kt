package com.example.projeto_barbearia.config.contracts

import com.example.projeto_barbearia.controllers.dtos.user.UserCreationRequest
import com.example.projeto_barbearia.controllers.dtos.user.UserCreationResponse
import com.example.projeto_barbearia.controllers.dtos.user.UserGetRequest
import com.example.projeto_barbearia.controllers.dtos.user.UserUpdateRequest
import com.example.projeto_barbearia.controllers.dtos.user.UserUpdateResponse
import java.util.UUID

interface UserService {

    fun create(user: UserStrategy): UserCreationResponse?
    fun getByEmail(email: String) : UserStrategy?
    fun getById(id : UUID) : UserStrategy?
    fun getByNationalCertificate(certificate: String) : UserStrategy?
    fun delete(user: UserStrategy) : Int
    fun getAll() : ArrayList<UserStrategy>
    fun update(id: UUID, data: UserUpdateRequest) : Int
}