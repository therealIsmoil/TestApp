package uz.gita.testapp.data.source.remote.response

import uz.gita.testapp.data.model.UserData

data class UserResponse(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val download_url: String
) {
    fun toUserData(): UserData {
        return UserData(
            id = id,
            author = author,
            width = width,
            height = height,
            url = url,
            download_url = download_url
        )
    }
}