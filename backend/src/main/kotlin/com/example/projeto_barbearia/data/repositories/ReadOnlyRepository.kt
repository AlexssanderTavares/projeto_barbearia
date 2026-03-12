package com.project.barbearia.data.repositories

import java.util.UUID
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.Repository
import java.util.Optional


@NoRepositoryBean
interface ReadOnlyRepository<T, UUID>: Repository<Any, Any> {

    fun findById(id: UUID): Optional<T>

    fun findAll(): List<T>
}