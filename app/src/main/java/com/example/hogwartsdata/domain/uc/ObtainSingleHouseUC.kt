package com.example.hogwartsdata.domain.uc

import com.example.hogwartsdata.domain.models.house.House
import com.example.hogwartsdata.domain.models.house.HouseEntity
import com.example.hogwartsdata.domain.repositories.houses.HousesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObtainSingleHouseUC @Inject constructor(private val housesRepository: HousesRepository) {
    suspend fun execute(id: String): Flow<BaseResult<HouseEntity, House>> {
        return housesRepository.getHouse(id)
    }

}