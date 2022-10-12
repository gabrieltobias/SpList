package com.example.android.shoppingList.apresentacao.view

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.ProdutoViewModelFactory
import com.example.android.shoppingList.apresentacao.ProdutosViewModel
import com.example.android.shoppingList.apresentacao.model.Produto
import com.example.android.shoppingList.dados.SpListApplication
import kotlin.random.Random

class EditarProduto: AppCompatActivity() {

    private val produtoViewModel: ProdutosViewModel by viewModels {
        ProdutoViewModelFactory((application as SpListApplication).repository_produtos)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edita_produto)


        val et_nomeProduto = findViewById<EditText>(R.id.nomeTextField)

        //Verificando se o usuario esta logado
        val intent_gerenciaProduto = intent
        val extras = intent_gerenciaProduto.extras
        val id_produto = extras?.getInt("id_produto")
        val nomeProduto = extras?.getString("nome_produto")
        val categoriaProduto = extras?.getString("categoria_produto")
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)
        et_nomeProduto.setText(nomeProduto)

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
            spinnerCategoria.setSelection(adapter.getPosition(categoriaProduto))
        }

        fun validation(): Boolean {
            if (et_nomeProduto.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "Insira um nome para o produto", Toast.LENGTH_LONG).show()
                return false
            }
            return true
        }

        btnSalvar.setOnClickListener {
            if(validation()){
                val produto = Produto(id_produto!!,et_nomeProduto.text.toString(),spinnerCategoria.getSelectedItem().toString())
                produtoViewModel.insert(produto)
                Toast.makeText(applicationContext,"Produto atualizado com sucesso", Toast.LENGTH_SHORT).show()
                //startActivity(intent_mainActivity)
            }
        }


    }
}