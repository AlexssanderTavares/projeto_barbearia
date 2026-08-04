package com.example.projeto_barbearia.data.models

import com.example.projeto_barbearia.config.contracts.UserStrategy
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID


@Entity(name = "filial")
@Table(name = "filial")
data class Filial(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_filial")
    override var id: UUID? = null,

    @Column(name = "cnpj")
    override val nationalCertificate: String? = "",

    @Column(name = "nome")
    override val name: String,

    @Column(name = "email")
    override val email: String,

    @Column(name = "pass")
    override val pass: String,

    ) : UserDetails, UserStrategy{

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority("ROLE_BUSINESS"))
    }

    override fun getPassword(): String? {
        return pass
    }

    override fun getUsername(): String {
        return email
    }

}
