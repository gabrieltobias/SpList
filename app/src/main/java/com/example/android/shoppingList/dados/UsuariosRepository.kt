package com.example.android.shoppingList.dados

import androidx.lifecycle.LiveData
import com.example.android.shoppingList.apresentacao.model.Usuario

class UsuariosRepository(private val usuarioDao: UsuarioDao) {

    val usuarios: LiveData<List<Usuario>> = usuarioDao.getAllUsers()

    suspend fun insert(usuario: Usuario){
        return usuarioDao.insert(usuario)
    }

    suspend fun getUserName(userName: String): Usuario?{
        return usuarioDao.getUsername(userName)
    }
}