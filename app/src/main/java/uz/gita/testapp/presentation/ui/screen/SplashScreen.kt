package uz.gita.testapp.presentation.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import uz.gita.testapp.R
import uz.gita.testapp.data.source.local.MySharedPreference
import uz.gita.testapp.presentation.viewmodel.SplashScreenViewModel
import uz.gita.testapp.presentation.viewmodel.impl.SplashScreenViewModelImpl
import java.util.*

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewModel: SplashScreenViewModel by viewModels<SplashScreenViewModelImpl>()
    private lateinit var preference: MySharedPreference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        preference = MySharedPreference(requireContext())
        viewModel.openNextScreenLiveData.observe(viewLifecycleOwner, openNextScreenObserver)
        viewModel.notConnectionLiveData.observe(viewLifecycleOwner, notConnectionObserver)

        when (preference.language) {
            0 -> {
                setLanguage("ru")
            }
            1 -> {
                setLanguage("uz")
            }
            else -> {
                setLanguage("eng")
            }
        }

    }

    private val openNextScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_splashScreen_to_mainScreen)
    }

    private val notConnectionObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_splashScreen_to_notConnectionScreen)
    }

    private fun setLanguage(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.locale = locale
        requireContext().resources.updateConfiguration(
            configuration,
            requireContext().resources.displayMetrics
        )
    }
}