package com.example.android.shoppingList.apresentacao

import androidx.lifecycle.*
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import com.example.android.shoppingList.apresentacao.model.Produto
import com.example.android.shoppingList.dados.ListaDeComprasRepository
import com.example.android.shoppingList.dados.ProdutosRepository
import kotlinx.coroutines.launch

/**
Essa classe tem como objetivo definir um
ViewModel que serve como uma comunicação entre o repositorio e a UI
 */

class ProdutosViewModel(private val repository: ProdutosRepository) : ViewModel() {


    val todosOsProdutos: LiveData<List<Produto>> = repository.todosOsProdutos.asLiveData()

    /**
     * Usando coroutine para inserir os dados
     */
    fun insert(produto: Produto) = viewModelScope.launch {
        repository.insert(produto)
    }

    fun updateProduto(produto: Produto) = viewModelScope.launch {
        repository.updateProduto(produto)
    }

    fun deletaProduto(produto: Produto) = viewModelScope.launch {
        repository.deletaProduto(produto)
    }
}

class ProdutoViewModelFactory(private val repository: ProdutosRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProdutosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProdutosViewModel(repository) as T
        }
        throw IllegalArgumentException("Classe ViewModel não existe")
    }
}