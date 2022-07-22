package uz.gita.testapp.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.testapp.presentation.viewmodel.SettingsScreenViewModel

class SettingsScreenViewModelImpl : ViewModel(), SettingsScreenViewModel {
    override val backLiveData = MutableLiveData<Unit>()
    override val openLanguageDialogLiveData = MutableLiveData<Unit>()
    override val onClickCheckerLiveData = MutableLiveData<Boolean>()

    override fun onClickBack() {
        backLiveData.value = Unit
    }

    override fun onCLickLanguage() {
        openLanguageDialogLiveData.value = Unit
    }

    override fun switchCheck(state: Boolean) {
        onClickCheckerLiveData.value = state
    }

}