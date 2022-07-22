package uz.gita.testapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.testapp.data.model.UserData

interface MainScreenUseCase {
    fun getUserData(): Flow<Result<List<UserData>>>
}