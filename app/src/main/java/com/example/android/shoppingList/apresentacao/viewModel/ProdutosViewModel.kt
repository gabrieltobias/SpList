package com.example.android.shoppingList.apresentacao

import androidx.lifecycle.*
import com.example.android.shoppingList.apresentacao.model.Produto
import com.example.android.shoppingList.dados.ItensListaRepository
import com.example.android.shoppingList.dados.ProdutosRepository
import kotlinx.coroutines.launch

/**
 Essa classe tem como objetivo definir um
 ViewModel que serve como uma comunicação entre o repositorio e a UI
 */

class ItensViewModel(private val repository: ProdutosRepository) : ViewModel() {


    val todosOsItens: LiveData<List<Produto>> = repository.todosOsProdutos.asLiveData()

    /**
     * Usando coroutine para inserir os dados
     */
    fun insert(produto: Produto) = viewModelScope.launch {
        repository.insert(produto)
    }
}

class ItensViewModelFactory(private val repository: ProdutosRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItensViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItensViewModelFactory(repository) as T
        }
        throw IllegalArgumentException("Classe ViewModel não existe")
    }
}
