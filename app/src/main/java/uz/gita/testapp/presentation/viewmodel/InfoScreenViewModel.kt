package uz.gita.testapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.testapp.data.model.UserData

interface InfoScreenViewModel {
    val setDataLiveData: LiveData<UserData>

    fun setData(userData: UserData)
}