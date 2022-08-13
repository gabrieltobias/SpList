package com.example.android.shoppingList.dados

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.shoppingList.apresentacao.model.Carrinho
import com.example.android.shoppingList.apresentacao.model.Produto
import kotlinx.coroutines.flow.Flow

interface CarrinhoDao{
    @Query("SELECT * FROM tb_carrinho WHERE fk_listaDeCompras = fk_listaDeCompras")
    //O tipo Flow sempre guarda a ultima versão dos dados, e notifica os seus observadores quando
    // os dados são modificados
    fun GetCarrinho(): Flow<List<Produto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(carrinho: Carrinho)

    @Query("DELETE FROM tb_carrinho")
    suspend fun deleteAll()
}