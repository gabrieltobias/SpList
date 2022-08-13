package com.example.android.shoppingList.dados

import androidx.annotation.WorkerThread
import com.example.android.shoppingList.apresentacao.model.Carrinho
import com.example.android.shoppingList.apresentacao.model.Produto
import kotlinx.coroutines.flow.Flow

class CarrinhoRepository(private val carrinhoDao: CarrinhoDao) {

    // Variavel do tipo Flow quando observada notifica sobre as mudanças no dado
    val retornaCarrinho: Flow<List<Produto>> = carrinhoDao.GetCarrinho()

    // Garantindo que não exista nenhum trabalho sendo rodado fora da MainThread
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(carrinho: Carrinho) {
        carrinhoDao.insert(carrinho)
    }

}