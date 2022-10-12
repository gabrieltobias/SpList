package com.example.android.shoppingList.apresentacao.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.ListaViewModelFactory
import com.example.android.shoppingList.apresentacao.ListasViewModel
import com.example.android.shoppingList.apresentacao.model.Usuario
import com.example.android.shoppingList.apresentacao.viewModel.LoginViewModel
import com.example.android.shoppingList.apresentacao.viewModel.LoginViewModelFactory
import com.example.android.shoppingList.dados.SpListApplication
import kotlin.random.Random

class RegistrarUsuario : AppCompatActivity() {

    //var isExist = false

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((application as SpListApplication).repository_Usuarios)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        val et_primeiroNome = findViewById<EditText>(R.id.primeiroNomeTextField)
        val et_segundoNome = findViewById<EditText>(R.id.segundoNomeTxtField)
        val et_usuario = findViewById<EditText>(R.id.usuarioTextField)
        val et_senha = findViewById<EditText>(R.id.senhaTextField)


        fun validation(): Boolean {

            if (et_primeiroNome.text.isNullOrEmpty()) {
                Toast.makeText(this@RegistrarUsuario, "Insira o primeiro nome", Toast.LENGTH_LONG).show()
                return false
            }

            if (et_segundoNome.text.isNullOrEmpty()) {
                Toast.makeText(this@RegistrarUsuario, "Insira o segundo nome", Toast.LENGTH_LONG).show()
                return false
            }

            if (et_senha.text.isNullOrEmpty()) {
                Toast.makeText(this@RegistrarUsuario, "Insira uma senha", Toast.LENGTH_LONG).show()
                return false
            }
            return true
        }

        /*
        btnRegistrar.setOnClickListener {
            if(validation()){
                loginViewModel.getGetAllData().observe(this, object : Observer<List<Usuario>>{
                    override fun onChanged(t: List<Usuario>) {
                        var userObject = t
                        for (i in userObject.indices) {
                            if (userObject[i].Username?.equals(et_primeiroNome.text.toString())!!){
                                isExist = true
                                break
                            }else{
                                isExist = false
                                continue
                            }
                        }
                        if (isExist){
                            Toast.makeText(this@RegistrarUsuario, " Usuário ja registrado ", Toast.LENGTH_LONG)
                                .show()
                        }else{
                            var userId = Random.nextInt(1000000)
                            val primeiroNome = et_primeiroNome.text.toString()
                            var segundoNome = et_segundoNome.text.toString()
                            var usuario = et_usuario.text.toString()
                            var senha = et_senha.text.toString()
                            val user = Usuario(userId,primeiroNome,segundoNome,usuario,senha,"Usuario")
                            loginViewModel.insert(user)
                            Toast.makeText(applicationContext, "Usuario criado com sucesso",Toast.LENGTH_LONG).show()
                            println("buuuuuug")
                        }
                    }
                })
            }
        } */

        btnRegistrar.setOnClickListener {
            if(validation()){
                var userId = Random.nextInt(1000000)
                val primeiroNome = et_primeiroNome.text.toString()
                var segundoNome = et_segundoNome.text.toString()
                var usuario = et_usuario.text.toString()
                var senha = et_senha.text.toString()
                val user = Usuario(userId,primeiroNome,segundoNome,usuario,senha,"Usuario")
                loginViewModel.insert(user)
                Toast.makeText(applicationContext, "Usuario criado com sucesso",Toast.LENGTH_LONG).show()
                println("buuuuuug")
                //Redirect para a página de login
                val intent = Intent(this@RegistrarUsuario, Login::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(applicationContext,"Preencha todos os campos",Toast.LENGTH_SHORT).show()
            }

        }


    }


}