package uz.gita.testapp.data.repository

import uz.gita.testapp.data.model.UserData

interface UserRepository {
    suspend fun getUser(): Result<List<UserData>>
}