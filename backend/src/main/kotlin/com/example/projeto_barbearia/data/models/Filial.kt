package com.example.projeto_barbearia.data.models

import com.example.projeto_barbearia.config.contracts.UserStrategy
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
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
    val cnpj: String,

    @Column(name = "nome")
    override val name: String,

    @Column(name = "email")
    override val email: String,

    @Column(name = "pass")
    override val pass: String,

    @Column(name = "qtd_profissionais")
    val qtProf: Int,
    ) : UserDetails, UserStrategy{

    override fun getAuthorities(): Collection<GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getPassword(): String? {
        TODO("Not yet implemented")
    }

    override fun getUsername(): String {
        TODO("Not yet implemented")
    }

}
