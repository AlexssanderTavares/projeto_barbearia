package com.project.barbearia.data.models.views

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable
import java.sql.Timestamp

@Immutable
@Entity
@Table(name = "os_view")
data class OSView(

    @Id
    @Column(name = "id_os")
    val id: Long,

    @Column(name = "data")
    val data: Timestamp,

    @Column(name = "status")
    val status: String,

    @Column(name = "cliente")
    val cliente: String,

    @Column(name = "profissional")
    val prof: String
)
