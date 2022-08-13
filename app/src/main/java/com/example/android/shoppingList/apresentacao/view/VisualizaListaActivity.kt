package com.example.android.shoppingList.apresentacao.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.ListaViewModelFactory
import com.example.android.shoppingList.apresentacao.ListasViewModel
import com.example.android.shoppingList.dados.SpListApplication
import com.google.android.material.floatingactionbutton.FloatingActionButton

class VisualizaListaActivity : AppCompatActivity() {

    private val listasViewModel: ListasViewModel by viewModels {
        ListaViewModelFactory((application as SpListApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualiza_lista)
        //Pegando o ID da lista selecionada a partir do MainActivity
        val bundle : Bundle? = intent.extras
        val id = bundle!!.getInt("id")
        var NomeLista = bundle!!.getString("nomeLista")
        var txtNomeLista = findViewById<TextView>(R.id.txtNomeLista)
        txtNomeLista.setText(NomeLista)


        //Botão que adiciona item a lista
        val btnAddItemLista = findViewById<FloatingActionButton>(R.id.add_item_lista)
        btnAddItemLista.setOnClickListener {
            val intent = Intent(this@VisualizaListaActivity,SelecionaItensActivity::class.java)
            startActivity(intent)
        }
    }
}