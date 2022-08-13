package com.example.android.shoppingList.apresentacao

import androidx.lifecycle.*
import com.example.android.shoppingList.apresentacao.model.Item
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import com.example.android.shoppingList.dados.ItensRepository
import com.example.android.shoppingList.dados.ListaDeComprasRepository
import kotlinx.coroutines.launch

/**
 Essa classe tem como objetivo definir um
 ViewModel que serve como uma comunicação entre o repositorio e a UI
 */

class ItensViewModel(private val repository: ItensRepository) : ViewModel() {


    val todosOsItens: LiveData<List<Item>> = repository.todosOsItens.asLiveData()

    /**
     * Usando coroutine para inserir os dados
     */
    fun insert(item: Item) = viewModelScope.launch {
        repository.insert(item)
    }
}

class ItensViewModelFactory(private val repository: ItensRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItensViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItensViewModel(repository) as T
        }
        throw IllegalArgumentException("Classe ViewModel não existe")
    }
}
