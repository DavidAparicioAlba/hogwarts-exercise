package com.example.hogwartsdata.ui.layouts.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hogwartsdata.core.ScreenState
import com.example.hogwartsdata.data.local.SharedPreferencesManager
import com.example.hogwartsdata.domain.models.user.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val sharedPreferencesManager: SharedPreferencesManager) : ViewModel() {

    private val state: MutableLiveData<ScreenState<LoginActivityState>> = MutableLiveData()
    val mState: LiveData<ScreenState<LoginActivityState>> get() = state

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        handleSession()
    }

    private fun showLoading(){
        this.state.value = ScreenState.Render(LoginActivityState.IsLoading(true))
    }

    private fun hideLoading(){
        state.value = ScreenState.Render(LoginActivityState.IsLoading(false))
    }

    fun doLogin(email: String, password: String){
        showLoading()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            sharedPreferencesManager.setUser(email)
            state.value = ScreenState.Render(LoginActivityState.SuccessLogin(UserEntity( email, password)))
        }
        //val result = doLoginUC(email.value!!, password.value!!)
        hideLoading()
    }

    fun handleSession() {
        if (isLogged()) {
            state.value = ScreenState.Render(LoginActivityState.SuccessLogin(
                UserEntity(sharedPreferencesManager.getUser().toString(), "1234")))
        }
    }
    fun isLogged(): Boolean {
        return !sharedPreferencesManager.getUser().isNullOrEmpty()
    }

}