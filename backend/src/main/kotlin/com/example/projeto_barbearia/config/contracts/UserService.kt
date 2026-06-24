package com.example.projeto_barbearia.config.contracts

interface UserService {

    fun getByEmail(email: String) : UserStrategy?
}