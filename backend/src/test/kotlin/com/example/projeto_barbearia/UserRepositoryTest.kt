package com.example.projeto_barbearia

import com.example.projeto_barbearia.config.TestcontainersConfiguration
import com.project.barbearia.data.models.Cliente
import org.aspectj.lang.annotation.After

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.junit.platform.commons.function.Try.success
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.testcontainers.postgresql.PostgreSQLContainer

@Import(TestcontainersConfiguration::class)
@SpringBootTest
class UserRepositoryTest {
}