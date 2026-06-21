package com.example.projeto_barbearia

import com.example.projeto_barbearia.config.TestcontainersConfiguration
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TestcontainersConfiguration::class)
@SpringBootTest
class ProjetoBarbeariaApplicationTests {

	@Test
	fun contextLoads() {
	}

}
