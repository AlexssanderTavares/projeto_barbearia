package com.project.barbearia

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BarbeariaApplication

fun main(args: Array<String>) {

    /*** Remova o comentário em caso de ser necessário um arquivo .env */
    //Dotenv.configure().directory("src/main/resources/").filename(".env").load()
	runApplication<BarbeariaApplication>(*args)
}
