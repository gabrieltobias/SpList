package com.example.android.shoppingList.dados

import androidx.room.*
import com.example.android.shoppingList.apresentacao.model.ItensLista
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import com.example.android.shoppingList.apresentacao.model.Produto
import kotlinx.coroutines.flow.Flow

@Dao
interface ItensListaDao{

    @Query("SELECT * FROM tb_itensLista WHERE fk_listaDeCompras=:FkListaDeCompras ORDER BY NomeProduto ASC")
    fun GetItensListaById(FkListaDeCompras: Int): Flow<List<ItensLista>>

    @Query("SELECT * FROM tb_itensLista")
    fun GetItensLista(): Flow<List<ItensLista>>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(itensLista: ItensLista)

    @Delete
    suspend fun deletaItemLista(itensLista: ItensLista)

}