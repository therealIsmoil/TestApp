package uz.gita.testapp.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.testapp.presentation.viewmodel.SplashScreenViewModel
import uz.gita.testapp.utils.isConnected

class SplashScreenViewModelImpl : ViewModel(), SplashScreenViewModel {
    override val openNextScreenLiveData = MutableLiveData<Unit>()
    override val notConnectionLiveData = MutableLiveData<Unit>()

    init {
        viewModelScope.launch {
            delay(1000)
            if (!isConnected()) {
                notConnectionLiveData.value = Unit
                return@launch
            }
            openNextScreenLiveData.value = Unit
        }
    }
}