package com.example.hogwartsdata.domain.models.house

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.example.hogwartsdata.domain.models.trait.TraitEntity
import com.example.hogwartsdata.domain.models.head.HeadEntity
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class HouseEntity(
    val animal: String,
    val commonRoom: String,
    val element: String,
    val founder: String,
    val ghost: String,
    val heads: @RawValue List<HeadEntity>,
    val houseColours: String,
    val id: String,
    val name: String,
    val traits: @RawValue List<TraitEntity>
): Parcelable {
    companion object : Parceler<HouseEntity> {
            override fun create(parcel: Parcel): HouseEntity {
                return HouseEntity(parcel)
            }

        override fun HouseEntity.write(parcel: Parcel, flags: Int) {
            parcel.writeString(animal)
            parcel.writeString(commonRoom)
            parcel.writeString(element)
            parcel.writeString(founder)
            parcel.writeString(ghost)
            listOf<HeadEntity>().apply {
                parcel.writeList(this)
            }
            parcel.writeString(houseColours)
            parcel.writeString(id)
            parcel.writeString(name)
            listOf<TraitEntity>().apply {
                parcel.writeList(this)
            }
        }


    }
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        listOf<HeadEntity>().apply {
            parcel.readList(this, HeadEntity::class.java.classLoader)
        },
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        listOf<TraitEntity>().apply {
            parcel.readList(this, TraitEntity::class.java.classLoader)
        }
    )
}