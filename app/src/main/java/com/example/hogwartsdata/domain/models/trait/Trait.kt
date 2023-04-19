package com.example.hogwartsdata.domain.models.trait


import com.google.gson.annotations.SerializedName

data class Trait(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)