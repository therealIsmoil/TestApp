package uz.gita.testapp.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.testapp.data.model.UserData
import uz.gita.testapp.presentation.viewmodel.InfoScreenViewModel

class InfoScreenViewModelImpl : ViewModel(), InfoScreenViewModel {
    override val setDataLiveData = MutableLiveData<UserData>()

    override fun setData(userData: UserData) {
        setDataLiveData.value = userData
    }

}