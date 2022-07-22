package uz.gita.testapp.data.repository.impl

import uz.gita.testapp.data.model.UserData
import uz.gita.testapp.data.repository.UserRepository
import uz.gita.testapp.data.source.remote.api.UserApi
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UserRepository {
    override suspend fun getUser(): Result<List<UserData>> {
        val response = api.getUsersData()

        return when (response.code()) {
            200 -> {
                Result.success(response.body()!!.map {
                    it.toUserData()
                })
            }
            403 -> {
                Result.failure(Exception(response.message()))
            }
            else -> {
                Result.failure(Exception("Something went wrong"))
            }
        }
    }
}