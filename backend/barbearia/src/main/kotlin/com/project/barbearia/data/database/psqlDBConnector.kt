package com.project.barbearia.data.database

import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class psqlDBConnector {

    companion object {
        var connectionStatus: Boolean = false
        val CONNECTION: Connection = connect()

        fun connect(): Connection {
            val vault: Dotenv = dotenv()
            val dbUser: String = vault["PSQL_USER"]
            val pass: String = vault["PSQL_PASS"]
            val url: String = "jdbc:postgresql://localhost:5432/projeto_barbearia"

            lateinit var dbConnection: Connection

            try{
                val driver = Class.forName("org.postgresql.Driver")
                dbConnection = DriverManager.getConnection(url, dbUser, pass)
            } catch(e: SQLException){
                println("Conexão ao servidor PostgreSQL falhou devido à: ")
                println(e.message)
            } catch (e: ClassNotFoundException){
                println("A Driver Class não foi encontrada")
                println(e.message)
            }

            val setDatabase = dbConnection.prepareStatement("USE projeto_barbearia;")

            try{
                setDatabase.execute()
                connectionStatus = true
                println("Usando banco de dados projeto_barbearia")
            } catch (e: SQLException) {
                println("Banco de dados fechado OU não existe")
                println(e.message)
            }

            return dbConnection
        }
    }
}