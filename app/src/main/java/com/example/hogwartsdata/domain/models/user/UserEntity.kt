package com.example.hogwartsdata.domain.models.user

data class UserEntity(
    val displayName: String,
    val password: String
) {

    companion object {
        var preloadUsers = listOf<UserEntity>(
            UserEntity("Paco", "1111"),
            UserEntity( "Raquel", "2222")
        )
    }
}