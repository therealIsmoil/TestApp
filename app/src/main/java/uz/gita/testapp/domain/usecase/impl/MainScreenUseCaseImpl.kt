package uz.gita.testapp.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.testapp.data.model.UserData
import uz.gita.testapp.data.repository.UserRepository
import uz.gita.testapp.domain.usecase.MainScreenUseCase
import javax.inject.Inject

class MainScreenUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : MainScreenUseCase {
    override fun getUserData(): Flow<Result<List<UserData>>> = flow {
        emit(repository.getUser())
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)
}