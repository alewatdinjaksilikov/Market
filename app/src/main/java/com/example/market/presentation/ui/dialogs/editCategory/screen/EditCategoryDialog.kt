package com.example.market.presentation.ui.dialogs.editCategory.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.market.R
import com.example.market.data.models.AddCategoryRequestData
import com.example.market.databinding.DialogEditCategoryBinding
import com.example.market.presentation.ui.dialogs.add.vm.AddProductDialogViewModel
import com.example.market.presentation.ui.dialogs.editCategory.vm.EditCategoryViewModel
import com.example.market.utils.EditCategoryClick
import com.example.market.utils.makeToast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditCategoryDialog:BottomSheetDialogFragment() {
    private lateinit var binding:DialogEditCategoryBinding
    private val viewModel : EditCategoryViewModel by viewModels()
    private val viewModel2 : AddProductDialogViewModel by viewModels()
    private val args : EditCategoryDialogArgs by navArgs()

    private var selectedCategoryId = 1
    private var isListAdded = false
    private val list = mutableListOf<String>()
    private var nameCategory = ""
    private var imageUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
        lifecycleScope.launch {
            viewModel.getCategoryById(args.id)
            viewModel2.getAllCategories()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_edit_category,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogEditCategoryBinding.bind(view)

        initVariables()
        initObservables()
        initListeners()

        binding.dropdownCategory.setOnItemClickListener { adapterView, view, i, l ->
            val item = adapterView.getItemAtPosition(i).toString()
            nameCategory = list[i]
            selectedCategoryId = i
        }

        viewModel2.getCategoriesFlow.onEach {
            if (!isListAdded){
                it.forEach { data ->
                    list.add(data.name)
                }
                isListAdded = true
            }
        }.launchIn(lifecycleScope)

        val adapterCategory =
            ArrayAdapter(requireContext(), R.layout.list_item_dropdown_menu, list)
        binding.dropdownCategory.setAdapter(adapterCategory)
    }

    private fun initObservables() {
        viewModel.getCategoryFLow.onEach {
            binding.etCategoryName.setText(it.name)
        }.launchIn(lifecycleScope)

        viewModel.editCategoryFlow.onEach {
            makeToast(it.message)
            if (it.statusCode==200){
                EditCategoryClick.buttonAddCategoryClick(true)
            }
            dismiss()
        }.launchIn(lifecycleScope)
    }

    private fun initVariables() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("nameCategory")?.observe(viewLifecycleOwner) {result ->
            nameCategory = result
            binding.dropdownCategory.setText(result)
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("imageUrl")?.observe(viewLifecycleOwner) {result ->
            imageUrl = result
        }
    }

    private fun initListeners() {

        binding.btnEdit.setOnClickListener {
            val name = binding.etCategoryName.text.toString()
            if (name.isNotEmpty() && nameCategory.isNotEmpty() && imageUrl.isNotEmpty()){
                lifecycleScope.launch {
                    viewModel.editCategory(
                        AddCategoryRequestData(
                            name = name,
                            imageUrl = imageUrl
                        ),
                        id = args.id
                    )
                }
            }else{
                makeToast("Заполните все поля!")
                Log.d("ASD",name)
                Log.d("ASD",nameCategory)
                Log.d("ASD",imageUrl)
            }
        }

        binding.btnEditProductImage.setOnClickListener {
            findNavController().navigate(EditCategoryDialogDirections.actionEditCategoryDialogToImagesFragment2())
        }
    }
}