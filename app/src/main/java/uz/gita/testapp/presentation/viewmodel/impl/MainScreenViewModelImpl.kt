package uz.gita.testapp.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.testapp.data.model.UserData
import uz.gita.testapp.domain.usecase.MainScreenUseCase
import uz.gita.testapp.presentation.viewmodel.MainScreenViewModel
import uz.gita.testapp.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModelImpl @Inject constructor(
    private val useCase: MainScreenUseCase
) : ViewModel(), MainScreenViewModel {
    override val swipeRefreshLiveData = MutableLiveData<Boolean>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val userDataLiveData = MutableLiveData<List<UserData>>()
    override val errorLiveData = MutableLiveData<String>()
    override val onCLickItemLiveData = MutableLiveData<UserData>()
    override val openSettingsScreenLiveData = MutableLiveData<Unit>()

    init {
        loadData()
    }

    override fun loadData() {
        progressLiveData.value = true
        useCase.getUserData().onEach {
            it.onSuccess { listUsersData ->
                swipeRefreshLiveData.value = true
                progressLiveData.value = false
                userDataLiveData.value = listUsersData
            }
            it.onFailure { throwable ->
                progressLiveData.value = true
                swipeRefreshLiveData.value = false
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }

    override fun clickItem(userData: UserData) {
        onCLickItemLiveData.value = userData
    }

    override fun swipeRefresh() {
        swipeRefreshLiveData.value = isConnected()
        loadData()
    }

    override fun onClickSettings() {
        openSettingsScreenLiveData.value = Unit
    }


}