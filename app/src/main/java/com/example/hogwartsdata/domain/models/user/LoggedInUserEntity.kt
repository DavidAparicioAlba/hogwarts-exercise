package com.example.hogwartsdata.domain.models.user

data class LoggedInUserEntity(
    val userId: String,
    val displayName: String,
    val password: String
) {

    companion object {
        var preloadUsers = listOf<LoggedInUserEntity>(
            LoggedInUserEntity("0", "Paco", "1111"),
            LoggedInUserEntity("1", "Raquel", "2222")
        )
    }
}