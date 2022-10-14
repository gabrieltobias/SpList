package com.example.android.shoppingList.apresentacao.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.ProdutoViewModelFactory
import com.example.android.shoppingList.apresentacao.ProdutosViewModel
import com.example.android.shoppingList.apresentacao.model.Produto
import com.example.android.shoppingList.apresentacao.viewModel.LoginViewModel
import com.example.android.shoppingList.apresentacao.viewModel.LoginViewModelFactory
import com.example.android.shoppingList.dados.SpListApplication
import kotlin.random.Random

class CadastrarProduto: AppCompatActivity() {

    private val produtoViewModel: ProdutosViewModel by viewModels {
        ProdutoViewModelFactory((application as SpListApplication).repository_produtos)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_produto)

        //Verificando se o usuario esta logado
        val intent_mainActivity = intent
        val extras = intent_mainActivity.extras

        var nomeUsuario = "Convidado"
        var idUsuario = 1

        if (extras != null) {
            nomeUsuario = extras.getString("nome_usuario")!!
            idUsuario = extras.getInt("id_usuario")
        }



        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)
        val et_nomeProduto = findViewById<EditText>(R.id.nomeTextField)
        val et_valor = findViewById<EditText>(R.id.valorTextField)

        //Combobox de categoria
        val spinnerCategoria: Spinner = findViewById(R.id.spinnerCategoria)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.categorias_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerCategoria.adapter = adapter
        }
        val categoria = spinnerCategoria.getSelectedItem().toString()

        fun validation(): Boolean {
            if (et_nomeProduto.text.isNullOrEmpty()) {
                Toast.makeText(this@CadastrarProduto, "Insira um nome para o produto", Toast.LENGTH_LONG).show()
                return false
            }
            if (et_valor.text.isNullOrEmpty()) {
                Toast.makeText(this@CadastrarProduto, "Insira um valor", Toast.LENGTH_LONG).show()
                return false
            }
            return true
        }

        fun Editable.toDouble() = toString().toDouble()


        btnCadastrar.setOnClickListener {
            if(validation()){
                var idProduto = Random.nextInt(1000000)
                val intent_mainActivity = Intent(this@CadastrarProduto, MainActivity::class.java)
                intent_mainActivity.putExtra("Nome", nomeUsuario)
                intent_mainActivity.putExtra("idUsuario", idUsuario)
                val produto = Produto(idProduto,et_nomeProduto.text.toString(),categoria,et_valor.text.toDouble())
                produtoViewModel.insert(produto)
                Toast.makeText(applicationContext,"Produto cadastrado com sucesso",Toast.LENGTH_SHORT).show()
                startActivity(intent_mainActivity)
            }
        }



    }

}