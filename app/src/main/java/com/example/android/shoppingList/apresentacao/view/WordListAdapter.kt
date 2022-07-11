

package com.example.android.shoppingList.apresentacao.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.view.WordListAdapter.WordViewHolder
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras

class WordListAdapter : ListAdapter<ListaDeCompras, WordViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.NomeLista)
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            wordItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): WordViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return WordViewHolder(view)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<ListaDeCompras>() {
            override fun areItemsTheSame(oldItem: ListaDeCompras, newItem: ListaDeCompras): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: ListaDeCompras, newItem: ListaDeCompras): Boolean {
                return oldItem.NomeLista == newItem.NomeLista
            }
        }
    }
}
