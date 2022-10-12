package com.example.android.shoppingList.apresentacao.view

import android.app.Activity
import android.content.ClipData
import android.content.ClipData.Item
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
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
import com.google.android.material.navigation.NavigationView
import kotlin.random.Random.Default.nextInt


class MainActivity : AppCompatActivity() {

    private val novaListaActivityRequestCode = 1
    private val listasViewModel: ListasViewModel by viewModels {
        ListaViewModelFactory((application as SpListApplication).repository)
    }
    var idUsuario: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Cria o recyclerView usando a classe Adapter
        val adapter = ListasAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawer_layout)
        val navView : NavigationView = findViewById(R.id.nav_view)
        val header : View = navView.getHeaderView(0)
        val intent2 = Intent(this@MainActivity, Login::class.java)
        val intentProduto = Intent(this@MainActivity, CadastrarProduto::class.java)
        val intentGerProduto = Intent(this@MainActivity, GerenciarProdutos::class.java)


        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> Toast.makeText(applicationContext, "Home",Toast.LENGTH_LONG).show()
                R.id.nav_login -> startActivity(intent2)
                R.id.cdastrar_produto -> startActivity(intentProduto)
                R.id.gerenciar_produtos -> startActivity(intentGerProduto)
            }
            true
        }

        //Verificando se o usuario esta logado
        val intent_login = intent
        val extras = intent_login.extras

        if (extras != null) {
            if (extras.containsKey("Nome")) {
                val nomeUsuario = extras.getString("Nome")
                idUsuario = extras.getInt("idUsuario")
                val txtNomeUser = header.findViewById<TextView>(R.id.user_name)
                txtNomeUser.setText(nomeUsuario)
            }
        }

        //Função para visualizar uma lista a partir de um item
        adapter.setOnItemClickListener(object : ListasAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                //Criando o intent para o VisualizaLista
                val intent = Intent(this@MainActivity, VisualizaListaActivity::class.java)

                //Passando a lista que foi selecionada atraves do putExtra()
                val listaDeCompras = adapter.currentList.get(position)
                intent.putExtra("id",listaDeCompras.id)
                intent.putExtra("nomeLista",listaDeCompras.NomeLista)
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
        listasViewModel.retornaListasUsuario(idUsuario).observe(owner = this) { listas ->
            // Atualiza a cópia em cache do adaptador
            listas.let { adapter.submitList(it) }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == novaListaActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NovaListaActivity.EXTRA_REPLY)?.let { reply ->
                var id = nextInt(1000000)
                val listaDeCompras = ListaDeCompras(id,reply,idUsuario)
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
