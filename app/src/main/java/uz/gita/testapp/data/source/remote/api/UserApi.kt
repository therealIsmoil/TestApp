package uz.gita.testapp.data.source.remote.api

import retrofit2.Response
import retrofit2.http.GET
import uz.gita.testapp.data.source.remote.response.UserResponse

interface UserApi {
    @GET("v2/list")
    suspend fun getUsersData(): Response<List<UserResponse>>
}