package com.example.android.shoppingList.apresentacao.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Tabela que armazena as listas
@Entity(tableName = "tb_usuario")
data class Usuario(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "PrimeiroNome") var PrimeiroNome: String,
        @ColumnInfo(name = "SegundoNome") var SegundoNome: String,
        @ColumnInfo(name = "Username") var Username: String,
        @ColumnInfo(name = "Senha") var Senha: String,
        @ColumnInfo(name = "Nivel") var Nivel: String)
