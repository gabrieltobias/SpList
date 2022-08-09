

package com.example.android.shoppingList.dados

import androidx.room.*
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import kotlinx.coroutines.flow.Flow

/**
 * Neste arquivo são definidos os métodos que chamam as querys SQL
 */

@Dao
interface ListaDeComprasDao {


    @Query("SELECT * FROM tb_ListaDeCompras ORDER BY NomeLista ASC")
    //O tipo Flow sempre guarda a ultima versão dos dados, e notifica os seus observadores quando
    // os dados são modificados
    fun GetListas(): Flow<List<ListaDeCompras>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listaDeCompras: ListaDeCompras)

    @Query("DELETE FROM tb_ListaDeCompras")
    suspend fun deleteAll()

    @Query("SELECT * FROM tb_listadecompras WHERE id=:listaId")
    fun getLista(listaId: Int): ListaDeCompras

    @Update
    fun updateLista(listaDeCompras: ListaDeCompras)

    @Delete
    suspend fun deleteLista(listaDeCompras: ListaDeCompras)

}
