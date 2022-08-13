package com.example.android.shoppingList.apresentacao.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.ItensViewModel
import com.example.android.shoppingList.apresentacao.ItensViewModelFactory
import com.example.android.shoppingList.dados.SpListApplication

class SelecionaProdutosActivity : AppCompatActivity() {

    private val itensViewModel: ItensViewModel by viewModels {
        ItensViewModelFactory((application as SpListApplication).repository_produtos)
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
        itensViewModel.todosOsItens.observe(owner = this) { itens ->
            // Atualiza a cópia em cache do adaptador
            itens.let { adapter.submitList(it) }
        }


    }
}