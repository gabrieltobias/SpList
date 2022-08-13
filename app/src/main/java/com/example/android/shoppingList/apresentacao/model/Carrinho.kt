package com.example.android.shoppingList.apresentacao.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_carrinho")
data class Carrinho(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "fk_listaDeCompras") val FkListaDeCompras: Int,
        @ColumnInfo(name = "fk_produto") val FkProduto: Int)