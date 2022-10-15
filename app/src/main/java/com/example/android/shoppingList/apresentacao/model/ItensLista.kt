package com.example.android.shoppingList.apresentacao.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(

        tableName = "tb_itensLista",
        foreignKeys = arrayOf(
                ForeignKey(entity = Produto::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("fk_produto"),
                        onDelete = CASCADE),
                ForeignKey(entity = ListaDeCompras::class,
                          parentColumns = arrayOf("id"),
                          childColumns = arrayOf("fk_listaDeCompras"),
                          onDelete = CASCADE))
)

data class ItensLista(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "fk_listaDeCompras") val FkListaDeCompras: Int,
        @ColumnInfo(name = "fk_produto") val FkProduto: Int,
        @ColumnInfo(name = "NomeProduto") val NomeProduto: String,
        @ColumnInfo(name = "CategoriaItem") val CategoriaProduto: String,
        @ColumnInfo(name = "Valor") val ValorProduto: Double)

