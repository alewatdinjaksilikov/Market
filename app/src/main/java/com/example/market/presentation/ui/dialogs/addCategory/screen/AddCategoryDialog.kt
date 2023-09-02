package com.example.market.presentation.ui.dialogs.addCategory.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.data.models.AddCategoryRequestData
import com.example.market.databinding.DialogAddCategoryBinding
import com.example.market.presentation.ui.dialogs.addCategory.vm.AddCategoryDialogViewModel
import com.example.market.utils.AddButtonCategoryClick
import com.example.market.utils.makeToast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddCategoryDialog:BottomSheetDialogFragment() {
    private lateinit var binding: DialogAddCategoryBinding
    private val viewModel : AddCategoryDialogViewModel by viewModels()
    private var imageUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_add_category,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogAddCategoryBinding.bind(view)

        initVariables()
        initObservables()
        initListeners()

    }

    private fun initObservables() {
        viewModel.addedCategoryFlow.onEach {
            makeToast(it.message)
            if (it.statusCode == 201){
                AddButtonCategoryClick.buttonAddCategoryClick(true)
            }
            dismiss()
        }.launchIn(lifecycleScope)


        viewModel.messageCategoryFlow.onEach {
            makeToast(it)
        }.launchIn(lifecycleScope)
    }

    private fun initListeners() {
        binding.btnAddCategoryImage.setOnClickListener {
            findNavController().navigate(AddCategoryDialogDirections.actionAddCategoryDialogToImagesFragment2())
        }

        binding.btnAddCategory.setOnClickListener {
            lifecycleScope.launch {
                val name = binding.etCategoryName.text.toString()
                if (name.isNotEmpty() && imageUrl.isNotEmpty()){
                    viewModel.addCategory(
                        AddCategoryRequestData(
                            name = name,
                            imageUrl = imageUrl
                        )
                    )
                }else{
                    makeToast("Заполните все поля!")
                }
            }
        }
    }

    private fun initVariables() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("imageUrl")?.observe(viewLifecycleOwner) {result ->
            imageUrl = result
        }
    }
}