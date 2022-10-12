package com.example.android.shoppingList.apresentacao.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.ProdutoViewModelFactory
import com.example.android.shoppingList.apresentacao.ProdutosViewModel
import com.example.android.shoppingList.dados.SpListApplication
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GerenciarProdutos: AppCompatActivity() {

    private val produtosViewModel: ProdutosViewModel by viewModels {
        ProdutoViewModelFactory((application as SpListApplication).repository_produtos)
    }

    override fun onBackPressed() {
        // code here to show dialog
        super.onBackPressed()
        val intentMainActivity = Intent(this@GerenciarProdutos, MainActivity::class.java)
        startActivity(intentMainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerencia_produtos)

        //Cria o recyclerView usando a classe Adapter
        val adapter = ProdutosAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerGerenciaProdutos)
        val btnAddProduto = findViewById<FloatingActionButton>(R.id.btnAddProduto)
        val intentEditaProduto = intent
        val extras = intentEditaProduto.extras

        if (extras != null) {
            if (extras.containsKey("deletouProduto")) {
                Toast.makeText(applicationContext,"Produto deletado com sucesso!",Toast.LENGTH_SHORT).show()
            }
        }

        val id_produto = extras?.getInt("id_produto")
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val intentEditarProduto = Intent(this@GerenciarProdutos, EditarProduto::class.java)


        //Função para visualizar uma lista a partir de um item
        adapter.setOnItemClickListener(object : ProdutosAdapter.onProdutoClickListener{
            override fun onItemClick(position: Int) {
                val produtoAtual = adapter.currentList.get(position)
                intentEditarProduto.putExtra("id_produto",produtoAtual.id)
                intentEditarProduto.putExtra("nome_produto",produtoAtual.NomeProduto)
                intentEditarProduto.putExtra("categoria_produto",produtoAtual.CategoriaItem)
                startActivity(intentEditarProduto)
            }
        })


        // Adiciona um observer no LiveData retornado pelo todasAsListas
        produtosViewModel.todosOsProdutos.observe(owner = this) { itens ->
            // Atualiza a cópia em cache do adaptador
            itens.let { adapter.submitList(it) }
        }

        btnAddProduto.setOnClickListener {
            val intentCadastrarProduto = Intent(this@GerenciarProdutos,CadastrarProduto::class.java)
            startActivity(intentCadastrarProduto)
        }
    }

}