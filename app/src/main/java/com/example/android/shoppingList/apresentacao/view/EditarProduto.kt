package com.example.android.shoppingList.apresentacao.view

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
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

        //Verificando se o usuario esta logado
        val intent_gerProduto = intent
        val extras2 = intent_gerProduto.extras

        var nomeUsuario = "Convidado"
        var idUsuario = 1

        if (extras2 != null) {
            nomeUsuario = extras2.getString("nome_usuario")!!
            idUsuario = extras2.getInt("id_usuario")
        }


        val et_nomeProduto = findViewById<EditText>(R.id.nomeTextField)
        val et_valor = findViewById<EditText>(R.id.valorTextField)


        val intent_gerenciaProduto = intent
        val extras = intent_gerenciaProduto.extras
        val id_produto = extras?.getInt("id_produto")
        val nomeProduto = extras?.getString("nome_produto")
        val categoriaProduto = extras?.getString("categoria_produto")
        var valorProduto = extras?.getDouble("valor_produto")
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)
        val btnDeletarProduto = findViewById<ImageButton>(R.id.btnDeletaProduto)
        val intentGerenciaProdutos = Intent(this@EditarProduto,GerenciarProdutos::class.java)
        intentGerenciaProdutos.putExtra("nome_usuario", nomeUsuario)
        intentGerenciaProdutos.putExtra("id_usuario", idUsuario)
        et_nomeProduto.setText(nomeProduto)
        et_valor.setText(valorProduto.toString())

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
            if(et_valor.text.isNullOrEmpty()){
                Toast.makeText(applicationContext, "Insira um valor", Toast.LENGTH_LONG).show()
            }
            return true
        }

        btnSalvar.setOnClickListener {
            if(validation()){
                valorProduto = et_valor.text.toString().toDouble()
                val produto = Produto(id_produto!!,et_nomeProduto.text.toString(),spinnerCategoria.getSelectedItem().toString(),valorProduto!!)
                produtoViewModel.insert(produto)
                println("Valor: " + valorProduto)
                Toast.makeText(applicationContext,"Produto atualizado com sucesso", Toast.LENGTH_SHORT).show()
                startActivity(intentGerenciaProdutos)
            }
        }

        btnDeletarProduto.setOnClickListener {
            if (validation()) {
                val produto = Produto(
                    id_produto!!,
                    et_nomeProduto.text.toString(),
                    spinnerCategoria.getSelectedItem().toString(),
                    valorProduto!!
                )
                val builder = AlertDialog.Builder(this@EditarProduto)
                builder.setMessage("Deseja deletar esse produto?")
                    .setCancelable(false)
                    .setPositiveButton("Sim") { dialog, id ->
                        produtoViewModel.deletaProduto(produto)
                        intentGerenciaProdutos.putExtra("deletouProduto",1)
                        startActivity(intentGerenciaProdutos)
                    }
                    .setNegativeButton("Não") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
            }
        }

    }
}