package com.example.hogwartsdata.domain.models.house


import com.example.hogwartsdata.domain.models.trait.Trait
import com.example.hogwartsdata.domain.models.head.Head
import com.example.hogwartsdata.domain.models.head.HeadEntity
import com.google.gson.annotations.SerializedName

data class House(
    @SerializedName("animal")
    val animal: String,
    @SerializedName("commonRoom")
    val commonRoom: String,
    @SerializedName("element")
    val element: String,
    @SerializedName("founder")
    val founder: String,
    @SerializedName("ghost")
    val ghost: String,
    @SerializedName("heads")
    val heads: List<Head>,
    @SerializedName("houseColours")
    val houseColours: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("traits")
    val traits: List<Trait>
) {
    fun toHouseEntity(): HouseEntity {
        return HouseEntity(animal, commonRoom, element, founder, ghost, heads.map { it.toHeadEntity() }, houseColours, id, name, traits.map { it.toTraitEntity() })
    }
}