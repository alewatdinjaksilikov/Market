package com.bizlergroup.stockcontrol.presentation.ui.dialogs.add.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bizlergroup.stockcontrol.R
import com.bizlergroup.stockcontrol.data.models.AddProductRequestData
import com.bizlergroup.stockcontrol.data.models.CategoryResponseData
import com.bizlergroup.stockcontrol.databinding.DialogAddProductBinding
import com.bizlergroup.stockcontrol.presentation.ui.dialogs.add.vm.AddProductDialogViewModel
import com.bizlergroup.stockcontrol.utils.AddButtonClick
import com.bizlergroup.stockcontrol.utils.makeToast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddProductDialog:BottomSheetDialogFragment() {
    private lateinit var binding: DialogAddProductBinding
    private val viewModel : AddProductDialogViewModel by viewModels()
//    private var selectedCategoryId = 1
    private var imageUrl = ""
    private var type = ""
    private var category = ""

    private var isListAdded = false
    val list = mutableListOf<CategoryResponseData>()
    private val listType = listOf("KG","M","L")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_add_product,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogAddProductBinding.bind(view)

        initVariables()
        initObservables()
        initListeners()

    }

    private fun initVariables() {
        lifecycleScope.launch {
            viewModel.getAllCategories()
        }
    }

    private fun initObservables() {
        viewModel.getCategoriesFlow.onEach {

            list.clear()
            list.addAll(it)

            val adapterCategory =
                ArrayAdapter(requireContext(), R.layout.list_item_dropdown_menu, list.map { it.name })
            binding.dropdownCategory.setAdapter(adapterCategory)
            adapterCategory.notifyDataSetChanged()
        }.launchIn(lifecycleScope)



        viewModel.addProductFlow.onEach {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            if (it.statusCode == 201){
                AddButtonClick.emitButtonClick(true)
            }
            dismiss()
        }.launchIn(lifecycleScope)
    }

    private fun initListeners() {

        binding.btnAddProduct.setOnClickListener {
            val name = binding.etProductName.text.toString()
            val amount = binding.etProductAmount.text.toString()
            val price = binding.etProductPrice.text.toString()
            val sizeProduct  = binding.etProductSize.text.toString()

            if (name!=="" && amount!="" && price!="" && type!="" && category!="" &&  sizeProduct!=""){
                lifecycleScope.launch{
                    viewModel.addProduct(AddProductRequestData(
                        amount = amount.toInt(),
                        category = category,
                        name = name,
                        price = price.toInt(),
                        unit = type,
                        size = sizeProduct
                    ))
                }
            }else{
                makeToast("Заполните все поля!")
            }
        }

        binding.btnCancel.setOnClickListener {
            dialog!!.dismiss()
        }

        binding.dropdownCategory.setOnItemClickListener { adapterView, view, i, l ->
            val item = adapterView.getItemAtPosition(i).toString()
            category = list[i].name
            imageUrl = list[i].imageUrl
            Toast.makeText(requireContext(),"Item $item",Toast.LENGTH_SHORT).show()
        }

        val adapterType = ArrayAdapter(requireContext(),R.layout.list_item_dropdown_menu,listType)
        binding.dropdownTypeProduct.setAdapter(adapterType)
        binding.dropdownTypeProduct.setOnItemClickListener { adapterView, view, i, l ->
            val item = adapterView.getItemAtPosition(i).toString()
            type = listType[i]
            Log.d("III",type)
            Toast.makeText(requireContext(),"Item $item",Toast.LENGTH_SHORT).show()
        }

    }
}