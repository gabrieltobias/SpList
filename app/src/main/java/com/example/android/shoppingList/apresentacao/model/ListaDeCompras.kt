package com.example.android.shoppingList.apresentacao.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Uma classe representando uma entidade que é uma linha na tabela do BD
 */

@Entity(tableName = "tb_ListaDeCompras")
data class ListaDeCompras(@PrimaryKey @ColumnInfo(name = "NomeLista") val NomeLista: String)
