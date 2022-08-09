
package com.example.android.shoppingList.dados

import androidx.annotation.WorkerThread
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio abstrato
 */
class ListaDeComprasRepository(private val listaDeComprasDao: ListaDeComprasDao) {

    // Variavel do tipo Flow quando observada notifica sobre as mudanças no dado
    val todasAsListas: Flow<List<ListaDeCompras>> = listaDeComprasDao.GetListas()

    // Garantindo que não exista nenhum trabalho sendo rodado fora da MainThread
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(listaDeCompras: ListaDeCompras) {
        listaDeComprasDao.insert(listaDeCompras)
    }
    suspend fun deleteLista(listaDeCompras: ListaDeCompras) = listaDeComprasDao.deleteLista(listaDeCompras)

}
