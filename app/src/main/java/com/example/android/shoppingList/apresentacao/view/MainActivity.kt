package com.example.android.shoppingList.apresentacao.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.ListasViewModel
import com.example.android.shoppingList.apresentacao.ListaViewModelFactory
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import com.example.android.shoppingList.dados.SpListApplication
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {

    private val novaListaActivityRequestCode = 1
    private val listasViewModel: ListasViewModel by viewModels {
        ListaViewModelFactory((application as SpListApplication).repository)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ListasAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

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

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                val listaDeCompras = adapter.currentList

                listasViewModel.deleteLista(listaDeCompras.get(pos))
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(recyclerView)
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val btnDeletaLista = findViewById<ImageButton>(R.id.btn_deletaLista)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NovaListaActivity::class.java)
            startActivityForResult(intent, novaListaActivityRequestCode)
        }


        
        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        listasViewModel.todasAsListas.observe(owner = this) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { adapter.submitList(it) }
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
