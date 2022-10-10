package com.example.android.shoppingList.apresentacao.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.android.shoppingList.apresentacao.model.ItensLista
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import com.example.android.shoppingList.apresentacao.model.Produto
import com.example.android.shoppingList.apresentacao.model.Usuario
import com.example.android.shoppingList.dados.ListaDeComprasRepository
import com.example.android.shoppingList.dados.SpListApplication
import com.example.android.shoppingList.dados.UsuariosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UsuariosRepository) : ViewModel() {
    private var getAllDatas: LiveData<List<Usuario>>

    init{
        getAllDatas = repository.getAllData()!!
    }
    fun insert(data: Usuario) = viewModelScope.launch{
        repository.insertData(data)
    }

    fun getGetAllData(): LiveData<List<Usuario>>{
        return getAllDatas
    }
}

class LoginViewModelFactory(private val repository: UsuariosRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Classe ViewModel não existe")
    }
}