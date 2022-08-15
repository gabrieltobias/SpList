package com.example.android.shoppingList.apresentacao.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.ProdutoViewModelFactory
import com.example.android.shoppingList.apresentacao.ProdutosViewModel
import com.example.android.shoppingList.dados.SpListApplication
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SelecionaProdutosActivity : AppCompatActivity() {

    private val produtosViewModel: ProdutosViewModel by viewModels {
        ProdutoViewModelFactory((application as SpListApplication).repository_produtos)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleciona_itens)

        //Cria o recyclerView usando a classe Adapter
        val adapter = ProdutosAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerSelecionaItem)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)




        // Adiciona um observer no LiveData retornado pelo todasAsListas
        produtosViewModel.todosOsProdutos.observe(owner = this) { itens ->
            // Atualiza a cópia em cache do adaptador
            itens.let { adapter.submitList(it) }
        }

        //Botão que salva os itens na lista
        val btnSalvarItens = findViewById<FloatingActionButton>(R.id.btn_salvar_itens_lista)
        btnSalvarItens.setOnClickListener {

        }


    }
}