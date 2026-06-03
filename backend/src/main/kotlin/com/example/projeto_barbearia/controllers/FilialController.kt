package com.example.projeto_barbearia.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/cpny")
class FilialController(

) {
    /*private val list: MutableList<Filial> = repository.findAll()
    @PostMapping("/new")
    fun create(@RequestBody filial: Filial) : ResponseEntity<Filial>{
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(filial))
        }catch(e: IllegalArgumentException){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null)
        }
    }

    @GetMapping("/list")
    fun getAll() : ResponseEntity<MutableList<Filial>>{
        return ResponseEntity.status(HttpStatus.OK).body(this.list)
    }

    @GetMapping("/login")
    fun login (@RequestBody filial: Filial) : ResponseEntity<Filial>{
        lateinit var filial: Filial

        try {
            this.list.forEach {
                if (it.email == filial.email && it.pass == filial.pass) {
                    filial = it
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(filial)
        } catch (e: Exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }*/
}