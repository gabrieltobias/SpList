package com.example.android.shoppingList.dados

import android.os.AsyncTask
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.shoppingList.apresentacao.model.ItensLista
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import com.example.android.shoppingList.apresentacao.model.Usuario
import kotlinx.coroutines.flow.Flow

class UsuariosRepository(private val usuarioDao: UsuarioDao) {

    private var allData: LiveData<List<Usuario>>? = null

    init{
        allData = usuarioDao.getDetails()
    }

    fun getAllData(): LiveData<List<Usuario>>? {
        return allData
    }

    fun insertUser(data: Usuario) {
        usuarioDao?.let { LoginInsertion(it).execute(data) }
    }

    private class LoginInsertion(private val daoAccess: UsuarioDao) :
        AsyncTask<Usuario, Void, Void>() {
        override fun doInBackground(vararg data: Usuario): Void? {
            daoAccess.insert(data[0])
            return null
        }
    }



}