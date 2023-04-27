package com.example.hogwartsdata.domain.repositories.houses

import com.example.hogwartsdata.data.remote.ApiService
import com.example.hogwartsdata.domain.models.head.HeadEntity
import com.example.hogwartsdata.domain.models.house.House
import com.example.hogwartsdata.domain.models.house.HouseEntity
import com.example.hogwartsdata.domain.models.trait.TraitEntity
import com.example.hogwartsdata.domain.uc.BaseResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HousesRepository @Inject constructor(val service: ApiService) : HousesRepositoryInteface {

    override suspend fun getHouses(): Flow<BaseResult<MutableList<HouseEntity>, House>> {
        return flow {
            val response = service.getHouses()
            if (response.isSuccessful){
                val body = response.body()!!
                val houses = mutableListOf<HouseEntity>()
                var house: HouseEntity
                val heads = mutableListOf<HeadEntity>()
                val traits = mutableListOf<TraitEntity>()
                body.forEach { houseResponse ->

                    houseResponse.heads.forEach {
                        heads.add(
                            HeadEntity(
                            it.firstName,
                            it.id,
                            it.lastName
                        )
                        )
                    }

                    houseResponse.traits.forEach {
                        traits.add(
                            TraitEntity(
                                it.id,
                                it.name
                            )
                        )
                    }

                    house = HouseEntity(
                        houseResponse.animal,
                        houseResponse.commonRoom,
                        houseResponse.element,
                        houseResponse.founder,
                        houseResponse.ghost,
                        heads,
                        houseResponse.houseColours,
                        houseResponse.id,
                        houseResponse.name,
                        traits)

                    houses.add(house)
                    heads.clear()
                    traits.clear()

                }
                emit(BaseResult.Success(houses))
            }else{
                val type = object : TypeToken<House>(){}.type
                val err = Gson().fromJson<House>(response.errorBody()!!.charStream(), type)!!
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun getHouse(id: String): Flow<BaseResult<HouseEntity, House>> {
        return flow {
            val response = service.getHouse(id)
            if (response.isSuccessful){
                val body = response.body()!!
                var house: HouseEntity
                val heads = mutableListOf<HeadEntity>()
                val traits = mutableListOf<TraitEntity>()

                body.heads.forEach {
                    heads.add(
                        HeadEntity(
                            it.firstName,
                            it.id,
                            it.lastName
                        )
                    )
                }

                body.traits.forEach {
                    traits.add(
                        TraitEntity(
                            it.id,
                            it.name
                        )
                    )
                }

                house = HouseEntity(
                    body.animal,
                    body.commonRoom,
                    body.element,
                    body.founder,
                    body.ghost,
                    heads,
                    body.houseColours,
                    body.id,
                    body.name,
                    traits)
                emit(BaseResult.Success(house))
            }else{
                val type = object : TypeToken<House>(){}.type
                val err = Gson().fromJson<House>(response.errorBody()!!.charStream(), type)!!
                emit(BaseResult.Error(err))
            }
        }
    }

}