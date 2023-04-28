package com.example.hogwartsdata.ui.layouts.login

import com.example.hogwartsdata.domain.models.user.User
import com.example.hogwartsdata.domain.models.user.UserEntity

sealed class LoginActivityState  {
    object Init : LoginActivityState()
    data class IsLoading(val isLoading: Boolean) : LoginActivityState()
    data class ShowToast(val message: String) : LoginActivityState()
    data class SuccessLogin(val loginEntity: UserEntity) : LoginActivityState()
    data class ErrorLogin(val rawResponse: User) : LoginActivityState()
}
