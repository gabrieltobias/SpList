package com.example.android.shoppingList.apresentacao.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.ProdutoViewModelFactory
import com.example.android.shoppingList.apresentacao.ProdutosViewModel
import com.example.android.shoppingList.apresentacao.model.ItensLista
import com.example.android.shoppingList.dados.SpListApplication
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

class GerenciarProdutos: AppCompatActivity() {

    private val produtosViewModel: ProdutosViewModel by viewModels {
        ProdutoViewModelFactory((application as SpListApplication).repository_produtos)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerencia_produtos)

        //Cria o recyclerView usando a classe Adapter
        val adapter = ProdutosAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerGerenciaProdutos)
        val btnAddProduto = findViewById<FloatingActionButton>(R.id.btnAddProduto)
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