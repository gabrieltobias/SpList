package com.example.android.shoppingList.apresentacao.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android.shoppingList.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class VisualizaListaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualiza_lista)
        //Pegando o ID da lista selecionada a partir do MainActivity
        val bundle : Bundle? = intent.extras
        val id = bundle!!.getInt("listaDeCompras")

        //Botão que adiciona item a lista
        val btnAddItemLista = findViewById<FloatingActionButton>(R.id.add_item_lista)
        btnAddItemLista.setOnClickListener {
            Toast.makeText(this, "TODO",Toast.LENGTH_LONG).show()
        }
    }
}