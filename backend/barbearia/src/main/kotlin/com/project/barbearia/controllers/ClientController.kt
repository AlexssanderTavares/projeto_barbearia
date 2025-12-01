package com.project.barbearia.controllers

import com.project.barbearia.data.models.Cliente
import com.project.barbearia.data.repositories.ClienteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/cli")
class ClientController(
    @Autowired
    private val repository: ClienteRepository
) {

    @PostMapping("/new")
    fun create(@RequestBody cli: Cliente): Cliente {
        return repository.save(cli)
    }

    @GetMapping("/list")
    fun getAll(): ResponseEntity<MutableList<Cliente>> {
        val list: MutableList<Cliente> = repository.findAll()
        return ResponseEntity.status(HttpStatus.OK).body(list)
    }

    @GetMapping("/login")
    fun login(@RequestBody cli: Cliente): ResponseEntity<Cliente?> {
        val list: MutableList<Cliente> = repository.findAll()
        lateinit var cliente: Cliente

        list.forEach {
            if(it.email == cli.email && it.pass == it.pass){
                cliente = it
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(cliente)
    }


}
