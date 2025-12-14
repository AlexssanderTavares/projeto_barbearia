package com.project.barbearia.data.repositories

import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.Repository

@NoRepositoryBean
interface ReadOnlyRepository<T, ID> : Repository<T, ID> {
}