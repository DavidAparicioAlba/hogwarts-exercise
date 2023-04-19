package com.example.hogwartsdata.domain.models.house

import com.example.hogwartsdata.domain.models.trait.TraitEntity
import com.example.hogwartsdata.domain.models.head.HeadEntity


data class HouseEntity(
    val animal: String,
    val commonRoom: String,
    val element: String,
    val founder: String,
    val ghost: String,
    val heads: List<HeadEntity>,
    val houseColours: String,
    val id: String,
    val name: String,
    val traits: List<TraitEntity>
)