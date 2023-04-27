package com.example.hogwartsdata.ui.layouts.login

import com.example.hogwartsdata.domain.models.user.LoggedInUser
import com.example.hogwartsdata.domain.models.user.LoggedInUserEntity

sealed class LoginActivityState  {
    object Init : LoginActivityState()
    data class IsLoading(val isLoading: Boolean) : LoginActivityState()
    data class ShowToast(val message: String) : LoginActivityState()
    data class SuccessLogin(val loginEntity: LoggedInUserEntity) : LoginActivityState()
    data class ErrorLogin(val rawResponse: LoggedInUser) : LoginActivityState()
}
