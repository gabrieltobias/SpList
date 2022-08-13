package com.example.android.shoppingList.apresentacao.view

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.model.Item
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras

class ItensAdapter(): ListAdapter<Item, ItensAdapter.ItensViewHolder>(ITENS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItensAdapter.ItensViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_seleciona_item, parent, false)
        return ItensAdapter.ItensViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItensAdapter.ItensViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.NomeItem)
    }


    class ItensViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itensItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            itensItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): ItensViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recyclerview_item, parent, false)

                return ItensViewHolder(view)
            }
        }
    }

    companion object {
        private val ITENS_COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.NomeItem == newItem.NomeItem
            }
        }
    }

}