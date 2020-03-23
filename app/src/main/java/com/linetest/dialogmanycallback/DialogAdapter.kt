package com.linetest.dialogmanycallback

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*

class DialogAdapter(
    private val context : Context,
    private val listItmes: List<CheckList>
): RecyclerView.Adapter<DialogAdapter.ViewHolder>() {

    private var listener : ItemClickListener<String> ?= null

    fun setOnClickListenr(listener: ItemClickListener<String>){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return listItmes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            itemView.tvTitle.text = listItmes[adapterPosition].listItem

            itemView.setOnClickListener {
                listener?.onClick(adapterPosition, listItmes[adapterPosition].listItem)
            }
        }
    }
}