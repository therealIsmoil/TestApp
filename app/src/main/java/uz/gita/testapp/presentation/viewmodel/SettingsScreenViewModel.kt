package uz.gita.testapp.presentation.viewmodel

import androidx.lifecycle.LiveData

interface SettingsScreenViewModel {
    val backLiveData: LiveData<Unit>
    val openLanguageDialogLiveData: LiveData<Unit>
    val onClickCheckerLiveData: LiveData<Boolean>

    fun onClickBack()
    fun onCLickLanguage()
    fun switchCheck(state: Boolean)
}