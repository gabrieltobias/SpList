package com.example.android.shoppingList.apresentacao.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.ProdutoViewModelFactory
import com.example.android.shoppingList.apresentacao.ProdutosViewModel
import com.example.android.shoppingList.apresentacao.model.ItensLista
import com.example.android.shoppingList.apresentacao.viewModel.VisualizaItensViewModelFactory
import com.example.android.shoppingList.apresentacao.viewModel.VisualizaListaViewModel
import com.example.android.shoppingList.dados.SpListApplication
import kotlin.random.Random

class SelecionaProdutosActivity : AppCompatActivity() {

    private val produtosViewModel: ProdutosViewModel by viewModels {
        ProdutoViewModelFactory((application as SpListApplication).repository_produtos)
    }
    private val visualizaListaViewModel: VisualizaListaViewModel by viewModels {
        VisualizaItensViewModelFactory((application as SpListApplication).repository_ItensLista)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleciona_itens)

        //Cria o recyclerView usando a classe Adapter
        val adapter = ProdutosAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerSelecionaItem)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val bundle : Bundle? = intent.extras
        val id_lista = bundle!!.getInt("id_lista")
        var NomeLista = bundle!!.getString("nomeLista")

        //Função para visualizar uma lista a partir de um item
        adapter.setOnItemClickListener(object : ProdutosAdapter.onProdutoClickListener{
            override fun onItemClick(position: Int) {
                val produtoAtual = adapter.currentList.get(position)
                var itemId = Random.nextInt(1000000)
                var itemAdd = ItensLista(itemId,id_lista,produtoAtual.id,produtoAtual.NomeProduto,produtoAtual.CategoriaItem)

                val builder = AlertDialog.Builder(this@SelecionaProdutosActivity)
                builder.setMessage("Deseja adicionar o item: ${produtoAtual.NomeProduto} a lista?")
                        .setCancelable(false)
                        .setPositiveButton("Sim") { dialog, id ->
                            visualizaListaViewModel.insert(itemAdd)
                        }
                        .setNegativeButton("Não") { dialog, id ->
                            // Dismiss the dialog
                            dialog.dismiss()
                        }
                val alert = builder.create()
                alert.show()
            }
        })


        // Adiciona um observer no LiveData retornado pelo todasAsListas
        produtosViewModel.todosOsProdutos.observe(owner = this) { itens ->
            // Atualiza a cópia em cache do adaptador
            itens.let { adapter.submitList(it) }
        }



    }


}