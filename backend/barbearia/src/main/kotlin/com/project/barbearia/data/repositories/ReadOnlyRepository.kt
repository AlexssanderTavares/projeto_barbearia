package com.project.barbearia.data.repositories

import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.Repository
import java.util.Optional
import java.util.UUID

@NoRepositoryBean
interface ReadOnlyRepository<T, UUID>: Repository<T, UUID> {

    fun findById(id: UUID): Optional<T>

    fun findAll(): List<T>
}