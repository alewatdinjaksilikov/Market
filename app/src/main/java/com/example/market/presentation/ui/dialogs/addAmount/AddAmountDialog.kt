package com.example.market.presentation.ui.dialogs.addAmount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.market.R
import com.example.market.data.models.AddAmountRequestData
import com.example.market.databinding.DialogAddAmountBinding
import com.example.market.presentation.ui.dialogs.addAmount.vm.AddAmountDialogVM
import com.example.market.utils.AddAmountClick
import com.example.market.utils.makeToast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AddAmountDialog:BottomSheetDialogFragment() {

    private lateinit var  binding : DialogAddAmountBinding
    private val args : AddAmountDialogArgs by navArgs()
    private val viewModel : AddAmountDialogVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_add_amount,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogAddAmountBinding.bind(view)
        initVariables()
        initObservables()
        initListeners()
    }

    private fun initObservables() {
            viewModel.addAmountFlow.onEach {
                if (it!=null && it.statusCode == 200){
                    AddAmountClick.buttonAddAmountClick(true)
                    makeToast(it.message)
                }
                dismiss()
            }.launchIn(lifecycleScope)

        viewModel.messageAddAmountFlow.onEach {
            makeToast(it)
        }.launchIn(lifecycleScope)

        viewModel.errorAddAmountFlow.onEach {
            makeToast(it.toString())
        }.launchIn(lifecycleScope)
    }

    private fun initListeners() {
        binding.btnAddAmount.setOnClickListener {
            val name = binding.etProductName.text.toString()
            val amount = binding.etProductAmount.text.toString()
            if (name!="" && amount!=""){
                viewModel.addAmount(body = AddAmountRequestData(
                    amount = amount.toInt(),
                    name = name
                ))
            }else if (amount==""){
                makeToast("Заполните количество!")
            }else{
                makeToast("Заполните все поля!!!")
            }
        }

        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initVariables() {
        binding.etProductName.setText(args.name)
    }
}