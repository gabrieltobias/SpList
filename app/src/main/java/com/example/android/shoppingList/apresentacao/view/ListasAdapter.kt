

package com.example.android.shoppingList.apresentacao.view

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import com.example.android.shoppingList.apresentacao.view.ListasAdapter.ListaViewHolder
import kotlin.math.log

class ListasAdapter : ListAdapter<ListaDeCompras, ListaViewHolder>(LISTAS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaViewHolder {
        return ListaViewHolder.create(parent)
    }


    override fun onBindViewHolder(holder: ListaViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.NomeLista)
        holder.btnDeletaLista.setOnClickListener{
            println(current)
        }


    }

    class ListaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)
        public val btnDeletaLista: ImageButton = itemView.findViewById(R.id.btn_deletaLista)



        fun bind(text: String?) {
            wordItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): ListaViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return ListaViewHolder(view)
            }
        }
    }

    companion object {
        private val LISTAS_COMPARATOR = object : DiffUtil.ItemCallback<ListaDeCompras>() {
            override fun areItemsTheSame(oldItem: ListaDeCompras, newItem: ListaDeCompras): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: ListaDeCompras, newItem: ListaDeCompras): Boolean {
                return oldItem.NomeLista == newItem.NomeLista
            }
        }
    }
}
