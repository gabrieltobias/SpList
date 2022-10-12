package com.example.android.shoppingList.dados

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.android.shoppingList.apresentacao.model.Produto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutosDao{
    @Query("SELECT * FROM tb_produtos ORDER BY NomeProduto ASC")
    //O tipo Flow sempre guarda a ultima versão dos dados, e notifica os seus observadores quando
    // os dados são modificados
    fun GetProdutos(): Flow<List<Produto>>

    @Query("SELECT * FROM tb_produtos WHERE id=:itemId")
    fun GetProduto(itemId: Int): Produto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(produto: Produto)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateProduto(produto: Produto)

    @Query("DELETE FROM tb_produtos")
    suspend fun deleteAll()
}