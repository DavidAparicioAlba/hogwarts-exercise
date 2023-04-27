package com.example.hogwartsdata.data.remote

import com.example.hogwartsdata.domain.models.house.House
import com.example.hogwartsdata.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(Constants.HOUSES_END_POINT)
    suspend fun getHouses(): Response<List<House>>

    @GET(Constants.HOUSES_END_POINT+"/{id}")
    suspend fun getHouse(@Path("id") id: String): Response<House>

}