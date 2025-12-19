package com.project.barbearia.controllers

import com.project.barbearia.data.models.Cliente
import com.project.barbearia.data.repositories.ClienteRepository
import com.project.barbearia.services.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
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
    private val service: ClientService
) {
    /*private val list: MutableList<Cliente> = repository.findAll()*/
    /*** Função que cria um novo cliente, a partir do preenchimento de um formulário de cadastro do
     * front-end, ao finalizar o preenchimento, um objeto JSON deve ser enviado via comunicação HTTP
     * (POST) no endereço abaixo
     * */
    @PostMapping("/new")
    fun create(@RequestBody cli: Cliente): ResponseEntity<Int> {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.create(cli))
        } catch (e: IllegalArgumentException){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null)
        }
    }

    /*** Função que lista todos os clientes registrados no banco de dados, a lista é retornada como um Array de
     * objetos JSON
     */
    /*@Bean
    @GetMapping("/list")
    fun getAll(): ResponseEntity<MutableList<Cliente>> {
        return ResponseEntity.status(HttpStatus.OK).body(this.list)
    }*/

    /*** Função que verifica um objeto JSON enviado pelo formulario do front-end que, corresponde com certos dados que
     * estão atribuidos a entidade cliente, se o Email e Senha coincidirem então o login pode ser realizado, caso não, retorna null como resposta
     */

    /*@GetMapping("/login")
    fun login(@RequestBody cli: Cliente): ResponseEntity<Cliente?> {
        lateinit var cliente: Cliente

        try {
            this.list.forEach {
                if (it.email == cli.email && it.pass == it.pass) {
                    cliente = it
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(cliente)
        } catch (e: Exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }*/
}
