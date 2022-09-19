package com.example.android.shoppingList.apresentacao.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Tabela que armazena as listas
@Entity(tableName = "tb_usuario")
data class Usuario(
        @PrimaryKey(autoGenerate = true)
        var userId: Int = 0,
        @ColumnInfo(name = "PrimeiroNome") val PrimeiroNome: String,
        @ColumnInfo(name = "SegundoNome") val SegundoNome: String,
        @ColumnInfo(name = "Username") val Username: String,
        @ColumnInfo(name = "Senha") val Senha: String,
        @ColumnInfo(name = "Nivel") val Nivel: String)
