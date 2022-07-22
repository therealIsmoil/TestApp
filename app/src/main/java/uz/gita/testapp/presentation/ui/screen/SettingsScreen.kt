package uz.gita.testapp.presentation.ui.screen

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.testapp.MainActivity
import uz.gita.testapp.R
import uz.gita.testapp.data.source.local.MySharedPreference
import uz.gita.testapp.databinding.ScreenSettingsBinding
import uz.gita.testapp.presentation.dialog.LanguageDialog
import uz.gita.testapp.presentation.viewmodel.SettingsScreenViewModel
import uz.gita.testapp.presentation.viewmodel.impl.SettingsScreenViewModelImpl
import java.util.*

class SettingsScreen : Fragment(R.layout.screen_settings) {
    private val binding by viewBinding(ScreenSettingsBinding::bind)
    private val viewModel: SettingsScreenViewModel by viewModels<SettingsScreenViewModelImpl>()
    private lateinit var preferences: MySharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onClickCheckerLiveData.observe(this@SettingsScreen, onClickCheckerObserver)
        viewModel.openLanguageDialogLiveData.observe(
            this@SettingsScreen,
            openLanguageDialogObserver
        )
        viewModel.backLiveData.observe(this@SettingsScreen, backObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        preferences = MySharedPreference(requireContext())
        binding.switchTheme.isChecked = preferences.theme

        when (preferences.language) {
            0 -> {
                binding.languageText2.text = "Русский"
            }
            1 -> {
                binding.languageText2.text = "O'zbekcha"
            }
            else -> {
                binding.languageText2.text = "English"
            }
        }

        binding.backButton.setOnClickListener {
            viewModel.onClickBack()
        }
        binding.languageLine.setOnClickListener {
            viewModel.onCLickLanguage()
        }
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            viewModel.switchCheck(isChecked)
        }
    }

    private val backObserver = Observer<Unit> {
        findNavController().popBackStack()
    }
    private val openLanguageDialogObserver = Observer<Unit> {
        val languageDialog = LanguageDialog()

        languageDialog.setOnClickRuLineListener {
            binding.languageText2.text = "Русский"
            setLanguage("ru")
            preferences.language = it
        }
        languageDialog.setOnClickUzLineListener {
            binding.languageText2.text = "O'zbekcha"
            setLanguage("uz")
            preferences.language = it
        }
        languageDialog.setOnClickEngLineListener {
            binding.languageText2.text = "English"
            setLanguage("eng")
            preferences.language = it
        }

        languageDialog.show(childFragmentManager, "languageDialog")
    }
    private val onClickCheckerObserver = Observer<Boolean> {
        preferences.theme = it
        if (it) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }
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

        findNavController().popBackStack()
    }


}