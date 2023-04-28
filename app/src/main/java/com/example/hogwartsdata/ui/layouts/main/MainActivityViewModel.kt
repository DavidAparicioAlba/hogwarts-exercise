package com.example.hogwartsdata.ui.layouts.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hogwartsdata.core.ScreenState
import com.example.hogwartsdata.data.local.SharedPreferencesManager
import com.example.hogwartsdata.domain.models.head.HeadEntity
import com.example.hogwartsdata.domain.uc.BaseResult
import com.example.hogwartsdata.domain.uc.ObtainHousesUC
import com.example.hogwartsdata.domain.uc.ObtainSingleHouseUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val obtainHousesUC: ObtainHousesUC,
    private val obtainSingleHouseUC: ObtainSingleHouseUC,
    private val sharedPreferencesManager: SharedPreferencesManager
): ViewModel() {

    private val _state: MutableLiveData<ScreenState<MainActivityScreenState>> = MutableLiveData()
    val state: LiveData<ScreenState<MainActivityScreenState>>
        get()= _state

    init {
        obtainHouses()
    }

    private fun setLoading(){
        this._state.value = ScreenState.Render(MainActivityScreenState.IsLoading(true))
    }

    private fun hideLoading(){
        _state.value = ScreenState.Render(MainActivityScreenState.IsLoading(false))
    }

    fun obtainHouses() {
        viewModelScope.launch {
            obtainHousesUC.execute()
                .onStart {
                    setLoading()
                }
                .catch {
                    hideLoading()
                }
                .collect { baseResult ->
                    hideLoading()
                    when(baseResult){
                        is BaseResult.Error -> _state.value = ScreenState.Render(MainActivityScreenState.ErrorShowHouses(baseResult.rawResponse))
                        is BaseResult.Success -> _state.value = ScreenState.Render(MainActivityScreenState.ShowHouses(baseResult.data))

                    }
                }
        }
    }

    fun obtainSingleHouse(id: String) {
        viewModelScope.launch {
            obtainSingleHouseUC.execute(id)
                .onStart {
                    setLoading()
                }
                .catch {
                    hideLoading()
                }
                .collect { baseResult ->
                    hideLoading()
                    when(baseResult){
                        is BaseResult.Error -> _state.value = ScreenState.Render(MainActivityScreenState.ErrorShowHouses(baseResult.rawResponse))
                        is BaseResult.Success -> _state.value = ScreenState.Render(MainActivityScreenState.ShowHouseInfo(baseResult.data))

                    }
                }
        }
    }
    fun addFavourite(head: String) {
        val favs = if(sharedPreferencesManager.getFavourites().isNullOrEmpty()) {
            mutableSetOf()
        } else {
            sharedPreferencesManager.getFavourites()
        }
        favs?.add(head)
        favs?.let { sharedPreferencesManager.setFavourites(it) }
    }

    fun getFavourites() {
        _state.value = ScreenState.Render(MainActivityScreenState.ShowFavouriteCharacters(sharedPreferencesManager.getFavourites()))
    }
}