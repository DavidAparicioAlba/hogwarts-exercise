package com.example.hogwartsdata.domain.models.user

import com.google.gson.annotations.SerializedName

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class User(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("password")
    val password: String
)