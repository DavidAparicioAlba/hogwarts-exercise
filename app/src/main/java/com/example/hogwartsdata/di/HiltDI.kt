package com.example.hogwartsdata.di

import android.content.Context
import com.example.hogwartsdata.data.local.SharedPreferencesManager
import com.example.hogwartsdata.data.remote.ApiService
import com.example.hogwartsdata.data.remote.HeaderInterceptor
import com.example.hogwartsdata.domain.repositories.houses.HousesRepository
import com.example.hogwartsdata.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HeaderInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            addInterceptor(interceptor)
            writeTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_END_POINT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

}

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object HouseModule {

    @Provides
    @Singleton
    fun provideHousesRepository(apiService: ApiService) : HousesRepository {
        return HousesRepository(apiService)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {
    @Provides
    fun providesSharedPref(@ApplicationContext context: Context) : SharedPreferencesManager {
        return  SharedPreferencesManager(context)
    }
}