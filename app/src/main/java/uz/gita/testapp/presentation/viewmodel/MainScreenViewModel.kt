package uz.gita.testapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.testapp.data.model.UserData

interface MainScreenViewModel {
    val swipeRefreshLiveData: LiveData<Boolean>
    val progressLiveData: LiveData<Boolean>
    val userDataLiveData: LiveData<List<UserData>>
    val errorLiveData: LiveData<String>
    val onCLickItemLiveData: LiveData<UserData>
    val openSettingsScreenLiveData: LiveData<Unit>


    fun loadData()
    fun clickItem(userData: UserData)
    fun swipeRefresh()
    fun onClickSettings()
}