package com.example.android.shoppingList.dados

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.android.shoppingList.apresentacao.model.ItensLista
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import com.example.android.shoppingList.apresentacao.model.Produto
import kotlinx.coroutines.flow.Flow

class ItensListaRepository(private val itensListaDao: ItensListaDao) {

    // Variavel do tipo Flow quando observada notifica sobre as mudanças no dado
    val retornaItensLista: Flow<List<ItensLista>>? = itensListaDao.GetItensLista()

    // Garantindo que não exista nenhum trabalho sendo rodado fora da MainThread
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(itensLista: ItensLista) {
        itensListaDao.insert(itensLista)
    }
    //Função que retorna os itens de uma lista especifica
    fun retornaItensLista(fk_lista : Int) : Flow<List<ItensLista>> {
        return itensListaDao.GetItensListaById(fk_lista)
    }
    fun retornaSomaLista(fk_lista: Int) : Flow<Float> {
        return itensListaDao.GetSomaLista(fk_lista)
    }

    suspend fun deletaItemLista(itensLista: ItensLista) = itensListaDao.deletaItemLista(itensLista)

}