package com.example.hogwartsdata.ui.layouts.heads

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hogwartsdata.R
import com.example.hogwartsdata.databinding.ItemHeadBinding
import com.example.hogwartsdata.domain.models.head.HeadEntity

class HeadsAdapter(var heads: List<String>, var onClick: (String) -> Unit): RecyclerView.Adapter<HeadsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemHeadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return heads.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(heads[position], position)
    }


    inner class ViewHolder(var view: ItemHeadBinding): RecyclerView.ViewHolder(view.root) {
        fun onBind(head: String, position: Int) {
            view.addFavouriteIv.setOnClickListener {
                view.addFavouriteIv.setImageResource(R.drawable.favourite)
                onClick(head)
            }
            view.tvCharacterName.text = head
        }
    }
}