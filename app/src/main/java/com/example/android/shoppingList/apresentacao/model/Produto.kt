package com.example.android.shoppingList.apresentacao.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//Tabela que armazena todos os itens
@Entity(tableName = "tb_produtos")
data class Produto(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "NomeProduto") val NomeProduto: String,
        @ColumnInfo(name = "CategoriaItem") val CategoriaItem: String)