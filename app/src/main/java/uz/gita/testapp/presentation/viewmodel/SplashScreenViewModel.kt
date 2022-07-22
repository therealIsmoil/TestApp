package uz.gita.testapp.presentation.viewmodel

import androidx.lifecycle.LiveData

interface SplashScreenViewModel {
    val openNextScreenLiveData: LiveData<Unit>
    val notConnectionLiveData: LiveData<Unit>
}