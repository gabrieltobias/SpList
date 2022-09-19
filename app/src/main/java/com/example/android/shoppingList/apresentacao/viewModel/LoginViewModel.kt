package com.example.android.shoppingList.apresentacao.viewModel

import androidx.lifecycle.*
import com.example.android.shoppingList.apresentacao.model.Usuario
import com.example.android.shoppingList.dados.UsuariosRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UsuariosRepository) : ViewModel() {

    val users = repository.usuarios

    val inputUsername = MutableLiveData<String>()

    val inputPassword = MutableLiveData<String>()

    //Function triggered When the Login Button is Clicked, Via Binding.
    fun loginButton() {
        //-------Logic for the code
    }

    fun signUP() {
        //navigate to the Register Fragment
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