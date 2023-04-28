package com.example.hogwartsdata.ui.layouts.main

import com.example.hogwartsdata.domain.models.head.HeadEntity
import com.example.hogwartsdata.domain.models.house.House
import com.example.hogwartsdata.domain.models.house.HouseEntity

sealed class MainActivityScreenState {
    object Init : MainActivityScreenState()
    data class IsLoading(val isLoading: Boolean) : MainActivityScreenState()
    data class ShowToast(val message: String) : MainActivityScreenState()
    data class ShowHouses(val houseListEntity: List<HouseEntity>) : MainActivityScreenState()
    data class ShowHouseInfo(val houseEntity: HouseEntity) : MainActivityScreenState()
    data class ShowFavouriteCharacters(val headList: MutableSet<String>) : MainActivityScreenState()
    data class ErrorShowHouses(val rawResponse: House) : MainActivityScreenState()
}