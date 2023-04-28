package com.example.hogwartsdata.domain.models.head

import android.os.Parcel
import android.os.Parcelable
import com.example.hogwartsdata.domain.models.house.HouseEntity
import com.example.hogwartsdata.domain.models.trait.TraitEntity
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeadEntity(
    val firstName: String,
    val id: String,
    val lastName: String
) : Parcelable {
    companion object : Parceler<HeadEntity> {
        override fun create(parcel: Parcel): HeadEntity {
            return HeadEntity(parcel)
        }
        override fun HeadEntity.write(parcel: Parcel, flags: Int) {
            parcel.writeString(firstName)
            parcel.writeString(id)
            parcel.writeString(lastName)
        }


    }
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
    )
    fun nameToString(headEntity: HeadEntity): String {
        return headEntity.firstName + " " + headEntity.lastName
    }

}
