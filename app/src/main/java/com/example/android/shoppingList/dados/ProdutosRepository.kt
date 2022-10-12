package com.example.android.shoppingList.dados

import androidx.annotation.WorkerThread
import com.example.android.shoppingList.apresentacao.model.ItensLista
import com.example.android.shoppingList.apresentacao.model.Produto
import kotlinx.coroutines.flow.Flow

class ProdutosRepository(private val produtosDao: ProdutosDao) {

    // Variavel do tipo Flow quando observada notifica sobre as mudanças no dado
    val todosOsProdutos: Flow<List<Produto>> = produtosDao.GetProdutos()

    // Garantindo que não exista nenhum trabalho sendo rodado fora da MainThread
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(produto: Produto) {
        produtosDao.insert(produto)
    }

    suspend fun updateProduto(produto: Produto){
        produtosDao.updateProduto(produto)
    }

}