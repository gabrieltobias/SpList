package com.example.android.shoppingList.dados

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SpListApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    //  Usando o by lazy os objetos são criados apenas quando forem usados
    //  Cria uma instancia do BD
    val database by lazy { ListasRoomDatabase.getDatabase(this, applicationScope) }
    // Cria uma instancia do repositorio com base no DAO
    val repository by lazy { ListaDeComprasRepository(database.listaDao()) }
    val repository_produtos by lazy { ProdutosRepository(database.produtosDao()) }
    val repository_ItensLista by lazy { ItensListaRepository(database.itensListaDao()) }
    val repository_Usuarios by lazy { UsuariosRepository(database.usuarioDao()) }
}
