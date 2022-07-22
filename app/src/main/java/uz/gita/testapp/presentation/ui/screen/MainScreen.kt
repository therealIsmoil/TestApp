package uz.gita.testapp.presentation.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.testapp.R
import uz.gita.testapp.data.model.UserData
import uz.gita.testapp.databinding.ScreenMainBinding
import uz.gita.testapp.presentation.ui.adapter.HorizontalAdapter
import uz.gita.testapp.presentation.ui.adapter.VerticalAdapter
import uz.gita.testapp.presentation.viewmodel.MainScreenViewModel
import uz.gita.testapp.presentation.viewmodel.impl.MainScreenViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainScreenViewModel by viewModels<MainScreenViewModelImpl>()
    private val horizontalAdapter = HorizontalAdapter()
    private val verticalAdapter = VerticalAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onCLickItemLiveData.observe(this@MainScreen, onClickItemObserver)
        viewModel.openSettingsScreenLiveData.observe(this@MainScreen, openSettingsScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.userDataLiveData.observe(viewLifecycleOwner, userDataObserver)
        viewModel.swipeRefreshLiveData.observe(viewLifecycleOwner, swipeRefreshObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)

        binding.recyclerHorizontal.adapter = horizontalAdapter
        binding.recyclerHorizontal.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.recyclerVertical.adapter = verticalAdapter
        binding.recyclerVertical.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        horizontalAdapter.setOnClickItemListener {
            viewModel.clickItem(it)
        }
        verticalAdapter.setOnClickItemListener {
            viewModel.clickItem(it)
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.swipeRefresh()
        }
        binding.settings.setOnClickListener {
            viewModel.onClickSettings()
        }
    }

    private val userDataObserver = Observer<List<UserData>> {
        horizontalAdapter.submitList(it)
        verticalAdapter.submitList(it)
    }
    private val onClickItemObserver = Observer<UserData> {
        val bundle = Bundle()
        bundle.putSerializable(
            "UserData", it
        )

        findNavController().navigate(R.id.action_mainScreen_to_infoScreen, bundle)
    }
    private val swipeRefreshObserver = Observer<Boolean> {
        binding.swipeRefresh.isRefreshing = !it
    }
    private val openSettingsScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_mainScreen_to_settingsScreen)
    }
    private val progressObserver = Observer<Boolean>{
        if (it){
            binding.progressBar.show()
        }else{
            binding.progressBar.hide()
        }
    }
}