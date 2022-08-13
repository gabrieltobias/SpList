package com.example.android.shoppingList.apresentacao.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.ListaViewModelFactory
import com.example.android.shoppingList.apresentacao.ListasViewModel
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import com.example.android.shoppingList.dados.SpListApplication
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {

    private val novaListaActivityRequestCode = 1
    private val listasViewModel: ListasViewModel by viewModels {
        ListaViewModelFactory((application as SpListApplication).repository)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adapter = ListasAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.setOnItemClickListener(object : ListasAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                //Toast.makeText(this@MainActivity, "Item n $position", Toast.LENGTH_LONG).show()
                val intent = Intent(this@MainActivity, VisualizaListaActivity::class.java)
                startActivity(intent)
            }
        })



        //Criando um objeto ItemTouchHelper
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }



            //Deletando lista quando o usuário faz o swipe
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                val listaDeCompras = adapter.currentList

                listasViewModel.deleteLista(listaDeCompras.get(pos))
                Toast.makeText(this@MainActivity,"Lista deletada",Toast.LENGTH_LONG).show()
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(recyclerView)
        }

        //Criando o botão que leva para a NovaListaActivity
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NovaListaActivity::class.java)
            startActivityForResult(intent, novaListaActivityRequestCode)
        }



        // Adiciona um observer no LiveData retornado pelo todasAsListas
        listasViewModel.todasAsListas.observe(owner = this) { listas ->
            // Atualiza a cópia em cache do adaptador
            listas.let { adapter.submitList(it) }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == novaListaActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NovaListaActivity.EXTRA_REPLY)?.let { reply ->
                var id = nextInt(1000000)
                val listaDeCompras = ListaDeCompras(id,reply)
                listasViewModel.insert(listaDeCompras)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.campo_vazio,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
