package com.project.barbearia.controllers

import com.project.barbearia.data.models.Filial
import com.project.barbearia.data.repositories.FilialRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/cpny")
class FilialController(
    @Autowired
    val repository: FilialRepository
) {
    private val list: MutableList<Filial> = repository.findAll()
    @PostMapping("/new")
    fun create(@RequestBody filial: Filial) : ResponseEntity<Filial?>{
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
    fun login (@RequestBody filial: Filial) : ResponseEntity<Filial?>{
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
    }
}