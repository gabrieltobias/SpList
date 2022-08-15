package com.example.android.shoppingList.apresentacao.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.viewModel.VisualizaItensViewModelFactory
import com.example.android.shoppingList.apresentacao.viewModel.VisualizaListaViewModel
import com.example.android.shoppingList.dados.SpListApplication
import com.google.android.material.floatingactionbutton.FloatingActionButton

class VisualizaListaActivity : AppCompatActivity() {

    private val visualizaListaViewModel: VisualizaListaViewModel by viewModels {
        VisualizaItensViewModelFactory((application as SpListApplication).repository_ItensLista)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualiza_lista)
        //Pegando o ID da lista selecionada a partir do MainActivity
        val bundle : Bundle? = intent.extras
        val id = bundle!!.getInt("id")
        var NomeLista = bundle!!.getString("nomeLista")

        //Cria o recyclerView usando a classe Adapter
        val adapter = VisualizaListaAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewVizualizaLista)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Adiciona um observer no LiveData retornado pelo todasAsListas
        visualizaListaViewModel.retornaItensLista(id).observe(owner = this) { itens ->
            // Atualiza a cópia em cache do adaptador
            itens.let { adapter.submitList(it) }
        }


        //Botão que adiciona item a lista
        val btnAddItemLista = findViewById<FloatingActionButton>(R.id.add_item_lista)
        btnAddItemLista.setOnClickListener {
            val intent = Intent(this@VisualizaListaActivity,SelecionaProdutosActivity::class.java)
            intent.putExtra("nomeLista",NomeLista)
            intent.putExtra("id_lista", id)
            startActivity(intent)
        }
    }
}