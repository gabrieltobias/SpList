package com.example.android.shoppingList.apresentacao.viewModel

import androidx.lifecycle.*
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import com.example.android.shoppingList.apresentacao.model.Usuario
import com.example.android.shoppingList.dados.UsuariosRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UsuariosRepository) : ViewModel() {

    val users = repository.usuarios
    val inputUsername = String()
    val inputPassword = MutableLiveData<String>()

    fun getUsername(userName: String) = viewModelScope.launch {
        repository.getUserName(userName)
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