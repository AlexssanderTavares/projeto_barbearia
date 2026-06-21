package com.example.projeto_barbearia.config

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.postgresql.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	var isStarted: Boolean = false

	@Bean
	@ServiceConnection
	fun postgresContainer(): PostgreSQLContainer {
		val container: PostgreSQLContainer? = PostgreSQLContainer(DockerImageName.parse("postgres:latest")).let {
			it.withDatabaseName("projeto_barbearia")
			it.withUsername("projeto_barbearia")
			it.withExposedPorts(5432)
		}

		if(container != null) {
			this.isStarted = true
		}

		return container!!
	}

}