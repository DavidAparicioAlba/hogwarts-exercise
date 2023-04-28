package com.example.hogwartsdata.ui.layouts.houses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hogwartsdata.R
import com.example.hogwartsdata.databinding.CardHouseBinding
import com.example.hogwartsdata.domain.models.house.HouseEntity

class HousesAdapter(var houses: List<HouseEntity>, var onClick: (HouseEntity) -> Unit, var showFavs: () -> Unit): RecyclerView.Adapter<HousesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = CardHouseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return houses.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(houses[position], position)
    }


    inner class ViewHolder(var view: CardHouseBinding): RecyclerView.ViewHolder(view.root) {
        fun onBind(house: HouseEntity, position: Int) {
            when(house.name.lowercase()) {
                "ravenclaw" -> view.houseIv.setImageResource(R.drawable.ravenclaw)
                "gryffindor" -> view.houseIv.setImageResource(R.drawable.gryffindor)
                "hufflepuff" -> view.houseIv.setImageResource(R.drawable.hufflepuff)
                "slytherin" -> view.houseIv.setImageResource(R.drawable.slytherin)

            }
            view.houseIv.setOnClickListener {
                onClick(house)
            }
            view.houseDescTv.setOnClickListener {
                showFavs()
            }
            view.tvHousesTitle.text = house.name
        }
    }
}