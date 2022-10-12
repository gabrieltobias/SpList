package com.example.android.shoppingList.apresentacao

import androidx.lifecycle.*
import com.example.android.shoppingList.apresentacao.model.ItensLista
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import com.example.android.shoppingList.dados.ListaDeComprasRepository
import kotlinx.coroutines.launch

/**
 Essa classe tem como objetivo definir um
 ViewModel que serve como uma comunicação entre o repositorio e a UI
 */

class ListasViewModel(private val repository: ListaDeComprasRepository) : ViewModel() {


    val todasAsListas: LiveData<List<ListaDeCompras>> = repository.todasAsListas.asLiveData()
    val listaUnica: LiveData<ListaDeCompras> = repository.listaUnica.asLiveData()

    fun retornaListasUsuario(fk_usuario: Int): LiveData<List<ListaDeCompras>>{
        return repository.listasUsuario(fk_usuario).asLiveData()
    }

    /**
     * Usando coroutine para inserir os dados
     */
    fun insert(listaDeCompras: ListaDeCompras) = viewModelScope.launch {
        repository.insert(listaDeCompras)
    }
    fun deleteLista(listaDeCompras: ListaDeCompras) = viewModelScope.launch {
        repository.deleteLista(listaDeCompras)
    }
}

class ListaViewModelFactory(private val repository: ListaDeComprasRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListasViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListasViewModel(repository) as T
        }
        throw IllegalArgumentException("Classe ViewModel não existe")
    }
}
