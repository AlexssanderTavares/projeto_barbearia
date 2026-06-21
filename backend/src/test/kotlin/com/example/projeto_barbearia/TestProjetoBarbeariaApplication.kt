package com.example.projeto_barbearia

import com.example.projeto_barbearia.config.TestcontainersConfiguration
import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<ProjetoBarbeariaApplication>().with(TestcontainersConfiguration::class).run(*args)
}
