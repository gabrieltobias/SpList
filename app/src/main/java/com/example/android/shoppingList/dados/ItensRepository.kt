package com.example.android.shoppingList.dados

import androidx.annotation.WorkerThread
import com.example.android.shoppingList.apresentacao.model.Item
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import kotlinx.coroutines.flow.Flow

class ItensRepository(private val itensDao: ItensDao) {

    // Variavel do tipo Flow quando observada notifica sobre as mudanças no dado
    val todosOsItens: Flow<List<Item>> = itensDao.GetItems()

    // Garantindo que não exista nenhum trabalho sendo rodado fora da MainThread
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(item: Item) {
        itensDao.insert(item)
    }

}