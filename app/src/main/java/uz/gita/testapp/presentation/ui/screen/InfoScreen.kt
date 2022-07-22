package uz.gita.testapp.presentation.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import uz.gita.testapp.R
import uz.gita.testapp.data.model.UserData
import uz.gita.testapp.databinding.ScreenInfoBinding
import uz.gita.testapp.presentation.viewmodel.InfoScreenViewModel
import uz.gita.testapp.presentation.viewmodel.impl.InfoScreenViewModelImpl

class InfoScreen : Fragment(R.layout.screen_info) {
    private val binding by viewBinding(ScreenInfoBinding::bind)
    private val viewModel: InfoScreenViewModel by viewModels<InfoScreenViewModelImpl>()
    private lateinit var userData: UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            userData = it.getSerializable("UserData") as UserData
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.setData(userData)
        viewModel.setDataLiveData.observe(viewLifecycleOwner, setDataObserver)
        viewModel.backLiveData.observe(viewLifecycleOwner, backObserver)

        binding.backIcon.setOnClickListener {
            viewModel.onCLickBack()
        }
    }

    private val setDataObserver = Observer<UserData> {
        Glide.with(binding.root)
            .load(it.download_url)
            .placeholder(R.drawable.place_holder)
            .centerCrop()
            .apply(RequestOptions().override(100, 100))
            .error(R.drawable.ic_launcher_background)
            .into(binding.infoImage)
        binding.infoName.text = it.author
    }
    private val backObserver = Observer<Unit> {
        findNavController().popBackStack()
    }
}