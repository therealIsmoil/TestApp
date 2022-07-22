package uz.gita.testapp.data.model

import java.io.Serializable

data class UserData(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val download_url: String
): Serializable
