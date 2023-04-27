package com.example.hogwartsdata.domain.models.head


import com.google.gson.annotations.SerializedName

data class Head(
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("lastName")
    val lastName: String
) {
    fun toHeadEntity(): HeadEntity {
        return HeadEntity(firstName, id, lastName)
    }
}
