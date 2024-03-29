package com.example.android.shoppingList.apresentacao.viewModel

import androidx.lifecycle.*
import com.example.android.shoppingList.apresentacao.model.ItensLista
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import com.example.android.shoppingList.dados.ItensListaRepository
import kotlinx.coroutines.launch

class VisualizaListaViewModel(private val repository: ItensListaRepository): ViewModel(){

    val todosOsItens: LiveData<List<ItensLista>> = repository.retornaItensLista!!.asLiveData()

    /**
     * Usando coroutine para inserir os dados
     */
    fun insert(itensLista: ItensLista) = viewModelScope.launch {
        repository.insert(itensLista)
    }

    fun retornaItensLista(fk_lista: Int): LiveData<List<ItensLista>>{
        return repository.retornaItensLista(fk_lista).asLiveData()
    }

    fun deletaItemLista(itensLista: ItensLista) = viewModelScope.launch {
        repository.deletaItemLista(itensLista)
    }
}

class VisualizaItensViewModelFactory(private val repository: ItensListaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VisualizaListaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VisualizaListaViewModel(repository) as T
        }
        throw IllegalArgumentException("Classe ViewModel não existe")
    }
}