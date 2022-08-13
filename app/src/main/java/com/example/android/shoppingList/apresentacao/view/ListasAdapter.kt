

package com.example.android.shoppingList.apresentacao.view

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import com.example.android.shoppingList.apresentacao.view.ListasAdapter.ListaViewHolder


class ListasAdapter() : ListAdapter<ListaDeCompras, ListaViewHolder>(LISTAS_COMPARATOR) {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaViewHolder {

        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)

        return ListaViewHolder.create(parent, mListener)
    }


    override fun onBindViewHolder(holder: ListaViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.NomeLista)
        
    }

    class ListaViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        private val listaItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            listaItemView.text = text
        }

        init {
            listaItemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }



        companion object {
            fun create(parent: ViewGroup, listener: onItemClickListener): ListaViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)

                return ListaViewHolder(view, listener)
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
