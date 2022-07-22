package uz.gita.testapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.testapp.data.model.UserData

interface InfoScreenViewModel {
    val setDataLiveData: LiveData<UserData>
    val backLiveData: LiveData<Unit>

    fun setData(userData: UserData)
    fun onCLickBack()
}