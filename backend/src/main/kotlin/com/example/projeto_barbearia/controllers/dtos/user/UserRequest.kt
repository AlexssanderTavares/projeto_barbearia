package com.example.projeto_barbearia.controllers.dtos.user

import com.example.projeto_barbearia.config.contracts.UserStrategy

data class UserRequest(var user: UserStrategy, val userType: String) {
}