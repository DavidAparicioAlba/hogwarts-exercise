package com.example.hogwartsdata.domain.uc

import com.example.hogwartsdata.domain.models.house.House
import com.example.hogwartsdata.domain.models.house.HouseEntity
import com.example.hogwartsdata.domain.repositories.houses.HousesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObtainHousesUC @Inject constructor(private val housesRepository: HousesRepository) {
    suspend fun execute(): Flow<BaseResult<List<HouseEntity>, House>> {
        return housesRepository.getHouses()
    }

}