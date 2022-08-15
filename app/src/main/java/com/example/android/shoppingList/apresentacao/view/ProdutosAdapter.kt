package com.example.android.shoppingList.apresentacao.view

import android.media.metrics.Event
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoppingList.R
import com.example.android.shoppingList.apresentacao.model.Produto

class ProdutosAdapter(): ListAdapter<Produto, ProdutosAdapter.ItensViewHolder>(ITENS_COMPARATOR) {

    private lateinit var mListener: onProdutoClickListener

    interface onProdutoClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onProdutoClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutosAdapter.ItensViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_seleciona_produto, parent, false)
        return ProdutosAdapter.ItensViewHolder.create(parent, mListener)
    }

    override fun onBindViewHolder(holder: ProdutosAdapter.ItensViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.NomeProduto)
    }

    class ItensViewHolder(itemView: View, listener: onProdutoClickListener) : RecyclerView.ViewHolder(itemView) {
        private val itensItemView: TextView = itemView.findViewById(R.id.txtViewNomeItem)
        fun bind(text: String?) {
            itensItemView.text = text
        }

        init {
            itensItemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        companion object {
            fun create(parent: ViewGroup, listener: onProdutoClickListener): ItensViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_seleciona_produto, parent, false)
                return ItensViewHolder(view,listener)
            }
        }

    }

    companion object {
        private val ITENS_COMPARATOR = object : DiffUtil.ItemCallback<Produto>() {
            override fun areItemsTheSame(oldItem: Produto, newItem: Produto): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Produto, newItem: Produto): Boolean {
                return oldItem.NomeProduto == newItem.NomeProduto
            }
        }
    }

}