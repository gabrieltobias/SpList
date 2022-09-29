package com.example.android.shoppingList.apresentacao.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.ListaViewModelFactory
import com.example.android.shoppingList.apresentacao.ListasViewModel
import com.example.android.shoppingList.apresentacao.viewModel.LoginViewModel
import com.example.android.shoppingList.apresentacao.viewModel.LoginViewModelFactory
import com.example.android.shoppingList.dados.SpListApplication
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class Login : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((application as SpListApplication).repository_Usuarios)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txtRegistrar = findViewById<TextView>(R.id.SignUpButton)
        val intentRegistro = Intent(this@Login, RegistrarUsuario::class.java)

        txtRegistrar.setOnClickListener {
            startActivity(intentRegistro)
        }
    }




}