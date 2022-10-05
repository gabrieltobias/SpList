package com.example.android.shoppingList.apresentacao.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
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
import kotlin.math.log

class Login : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((application as SpListApplication).repository_Usuarios)
    }

    var isExist = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txtRegistrar = findViewById<TextView>(R.id.SignUpButton)
        val intentRegistro = Intent(this@Login, RegistrarUsuario::class.java)
        var et_user_name = findViewById(R.id.userNameTextField) as EditText
        var et_password = findViewById(R.id.passwordTextField) as EditText
        var btn_submit = findViewById(R.id.submitButton) as Button

        fun validation(): Boolean {
            if (et_user_name.text.isNullOrEmpty()) {
                Toast.makeText(this@Login, "Insira o usuario", Toast.LENGTH_LONG).show()
                return false
            }
            if (et_password.text.isNullOrEmpty()) {
                Toast.makeText(this@Login, "Insira a senha", Toast.LENGTH_LONG).show()
                return false
            }
            return true
        }

        btn_submit.setOnClickListener {
            if (validation()) {

                loginViewModel.getGetAllData().observe(this, object : Observer<List<Usuario>> {
                    override fun onChanged(t: List<Usuario>) {
                        var userObject = t

                        for (i in userObject.indices) {
                            if (userObject[i].Username?.equals(et_user_name.text.toString())!!) {

                                if (userObject[i].Senha?.equals(et_password.text.toString())!!) {

                                    val intent = Intent(this@Login, MainActivity::class.java)
                                    intent.putExtra("Nome",userObject[i].PrimeiroNome)
                                    intent.putExtra("idUsuario",userObject[i].userId)
                                    // start your next activity
                                    startActivity(intent)

                                } else {
                                    Toast.makeText(this@Login, "Senha incorreta", Toast.LENGTH_LONG)
                                        .show()
                                }
                                isExist = true
                                break

                            } else {
                                isExist = false
                            }
                        }

                        if (isExist) {

                        } else {

                            Toast.makeText(this@Login, "Usuario ou senha incorreto(s)", Toast.LENGTH_LONG).show()
                        }

                    }

                })
            }
        }
        txtRegistrar.setOnClickListener {
            startActivity(intentRegistro)
        }
    }
}