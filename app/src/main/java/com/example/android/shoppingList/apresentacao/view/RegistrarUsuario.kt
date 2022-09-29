package com.example.android.shoppingList.apresentacao.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.ListaViewModelFactory
import com.example.android.shoppingList.apresentacao.ListasViewModel
import com.example.android.shoppingList.apresentacao.viewModel.LoginViewModel
import com.example.android.shoppingList.apresentacao.viewModel.LoginViewModelFactory
import com.example.android.shoppingList.dados.SpListApplication

class RegistrarUsuario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
    }
}