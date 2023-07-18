package com.example.market.presentation.ui.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.market.R
import com.example.market.data.models.AddProductRequestData
import com.example.market.databinding.DialogAddProductBinding
import com.example.market.presentation.vm.AddProductDialogViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddProductDialog:BottomSheetDialogFragment() {
    private lateinit var binding: DialogAddProductBinding
    private val viewModel : AddProductDialogViewModel by viewModels()
    private var selectedCategoryId = 1
    private var imageUrl = ""
    private var type = ""
    private var category = ""


    val list = mutableListOf<String>()
    val listType = listOf("M","G","L")

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

        getAllCategories(0)

        binding.btnAddProduct.setOnClickListener {
            val name = binding.etProductName.text.toString()
            val amount = binding.etProductAmount.text.toString()
            val price = binding.etProductPrice.text.toString()

            if (name!=="" && amount!="" && price!="" && type!="" && category!="" && imageUrl!=""){
                lifecycleScope.launch{
                    viewModel.addProduct(AddProductRequestData(
                        amount = amount.toInt(),
                        category = category,
                        imageUrl = imageUrl,
                        name = name,
                        price = price.toInt(),
                        unit = type
                    ))
                }
            }else{
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.addProductFlow.onEach {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            dismiss()
        }.launchIn(lifecycleScope)

        binding.btnCancel.setOnClickListener {
            dialog!!.dismiss()
        }

        binding.btnAddProductImage.setOnClickListener {
            Toast.makeText(requireContext(),"Находится в разработке !",Toast.LENGTH_SHORT).show()
        }


        binding.dropdownCategory.setOnItemClickListener { adapterView, view, i, l ->
            val item = adapterView.getItemAtPosition(i).toString()
            category = list[i]
            getAllCategories(i)
            Toast.makeText(requireContext(),"Item $item",Toast.LENGTH_SHORT).show()
        }

        val adapterType = ArrayAdapter<String>(requireContext(),R.layout.list_item_dropdown_menu,listType)
        binding.dropdownTypeProduct.setAdapter(adapterType)
        binding.dropdownTypeProduct.setOnItemClickListener { adapterView, view, i, l ->
            val item = adapterView.getItemAtPosition(i).toString()
            type = listType[i]
            Log.d("III",type)
            Toast.makeText(requireContext(),"Item $item",Toast.LENGTH_SHORT).show()
        }

    }

    private fun getAllCategories(int:Int) {
        lifecycleScope.launch {
            viewModel.getAllCategories()
        }

        viewModel.getCategoriesFlow.onEach {
            it.forEach { data ->
                list.add(data.name)
            }
            val adapterCategory =
                ArrayAdapter(requireContext(), R.layout.list_item_dropdown_menu, list)
            binding.dropdownCategory.setAdapter(adapterCategory)
            val e = it[int]
            selectedCategoryId = int
            imageUrl = e.imageUrl
        }.launchIn(lifecycleScope)
    }
}