package com.example.android.shoppingList.apresentacao.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.ListaViewModelFactory
import com.example.android.shoppingList.apresentacao.ListasViewModel
import com.example.android.shoppingList.apresentacao.model.Usuario
import com.example.android.shoppingList.apresentacao.viewModel.LoginViewModel
import com.example.android.shoppingList.apresentacao.viewModel.LoginViewModelFactory
import com.example.android.shoppingList.dados.SpListApplication
import com.example.android.shoppingList.dados.UsuarioDao
import com.example.android.shoppingList.dados.UsuariosRepository
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
        var et_user_name = findViewById(R.id.userNameTextField) as EditText
        var et_password = findViewById(R.id.passwordTextField) as EditText
        var btn_submit = findViewById(R.id.submitButton) as Button

        btn_submit.setOnClickListener {
            var inputUserName = et_user_name.text.toString()
            var inputPwd = et_password.text.toString()


            if(inputUserName == "" || inputPwd == ""){
                Toast.makeText(applicationContext, "Insira usuário e senha",Toast.LENGTH_SHORT).show()
            }else{
                //Inserir a logica de validacao no BD
                //https://suluksm.medium.com/how-to-create-a-login-register-app-with-kotlin-using-fragments-and-room-database-mvvm-76147970f754
                var userName = loginViewModel.getUsername(inputUserName!!)
            }

        }


        txtRegistrar.setOnClickListener {
            startActivity(intentRegistro)
        }
    }




}