package com.example.android.shoppingList.apresentacao.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.model.ItensLista
import com.example.android.shoppingList.apresentacao.model.Produto

class VisualizaListaAdapter(): ListAdapter<ItensLista, VisualizaListaAdapter.ItensViewHolder>(ITENS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisualizaListaAdapter.ItensViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_lista, parent, false)
        return VisualizaListaAdapter.ItensViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: VisualizaListaAdapter.ItensViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.NomeProduto)
    }

    class ItensViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itensItemView: TextView = itemView.findViewById(R.id.txtViewNomeItem)

        fun bind(text: String?) {
            itensItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): ItensViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recyclerview_item_lista, parent, false)

                return ItensViewHolder(view)
            }
        }
    }

    companion object {
        private val ITENS_COMPARATOR = object : DiffUtil.ItemCallback<ItensLista>() {
            override fun areItemsTheSame(oldItem: ItensLista, newItem: ItensLista): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: ItensLista, newItem: ItensLista): Boolean {
                return oldItem.NomeProduto == newItem.NomeProduto
            }
        }
    }

}