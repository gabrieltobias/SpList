package com.example.android.shoppingList.dados

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.shoppingList.apresentacao.model.ItensLista
import com.example.android.shoppingList.apresentacao.model.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao{

    @Insert
    suspend fun insert(usuario: Usuario)

    @Query("SELECT * FROM tb_usuario ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<Usuario>>

    @Query("SELECT * FROM tb_usuario WHERE Username LIKE :userName")
    fun getUsername(userName: String): Usuario?
}