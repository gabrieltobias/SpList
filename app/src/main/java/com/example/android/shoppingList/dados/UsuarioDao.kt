package com.example.android.shoppingList.dados

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.android.shoppingList.apresentacao.model.ItensLista
import com.example.android.shoppingList.apresentacao.model.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(usuario: Usuario)

    @Query("select * from tb_usuario")
    fun getDetails(): LiveData<List<Usuario>>

    @Query("DELETE FROM tb_usuario WHERE id = :id")
    fun deleteByUserId(id: Int)
}