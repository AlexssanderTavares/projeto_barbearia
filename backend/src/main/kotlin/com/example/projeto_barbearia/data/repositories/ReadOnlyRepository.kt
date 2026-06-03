package com.example.projeto_barbearia.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.Repository

import java.util.Optional

@NoRepositoryBean
interface ReadOnlyRepository<T : Any, ID : Any> : Repository<T, ID> {

    fun findById(id: ID): Optional<T>

    fun findAll(): List<T>

    fun count(): Long
}