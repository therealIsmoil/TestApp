package uz.gita.testapp.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.gita.testapp.R
import uz.gita.testapp.databinding.DialogLanguageBinding

class LanguageDialog : BottomSheetDialogFragment() {
    private val binding by viewBinding(DialogLanguageBinding::bind)
    private var onCLickRuLineListener: ((Int) -> Unit)? = null
    private var onCLickUzLineListener: ((Int) -> Unit)? = null
    private var onCLickEngLineListener: ((Int) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_language, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lineRu.setOnClickListener {
            onCLickRuLineListener?.invoke(0)
            dismiss()
        }
        binding.lineUz.setOnClickListener {
            onCLickUzLineListener?.invoke(1)
            dismiss()
        }
        binding.lineEng.setOnClickListener {
            onCLickEngLineListener?.invoke(2)
            dismiss()
        }
    }

    fun setOnClickRuLineListener(block: (Int) -> Unit) {
        onCLickRuLineListener = block
    }

    fun setOnClickUzLineListener(block: (Int) -> Unit) {
        onCLickUzLineListener = block
    }

    fun setOnClickEngLineListener(block: (Int) -> Unit) {
        onCLickEngLineListener = block
    }
}