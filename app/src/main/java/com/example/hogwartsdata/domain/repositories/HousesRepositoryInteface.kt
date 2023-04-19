package com.example.hogwartsdata.domain.repositories

import com.example.hogwartsdata.domain.models.house.House
import com.example.hogwartsdata.domain.models.house.HouseEntity
import com.example.hogwartsdata.domain.uc.BaseResult
import kotlinx.coroutines.flow.Flow

interface HousesRepositoryInteface {
    suspend fun getHouses(): Flow<BaseResult<List<HouseEntity>, House>>
    fun getHouse(id: String)
}