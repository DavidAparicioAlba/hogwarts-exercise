package com.example.hogwartsdata.domain.models.head

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeadEntity(
    val firstName: String,
    val id: String,
    val lastName: String
) : Parcelable {

}