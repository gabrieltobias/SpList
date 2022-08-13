package com.example.android.shoppingList.dados

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.shoppingList.apresentacao.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItensDao{
    @Query("SELECT * FROM tb_itens ORDER BY NomeItem ASC")
    //O tipo Flow sempre guarda a ultima versão dos dados, e notifica os seus observadores quando
    // os dados são modificados
    fun GetItems(): Flow<List<Item>>

    @Query("SELECT * FROM tb_itens WHERE id=:itemId")
    fun getItem(itemId: Int): Item

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Query("DELETE FROM tb_itens")
    suspend fun deleteAll()
}