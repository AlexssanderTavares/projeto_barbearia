package com.project.barbearia.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/endereco")
class EnderecoController {

    @GetMapping
    fun hello() : String {
        return "Hello"
    }
}