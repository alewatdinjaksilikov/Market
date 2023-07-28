package com.example.market.presentation.ui.dialogs.add.screen

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
import com.example.market.R
import com.example.market.data.models.AddProductRequestData
import com.example.market.databinding.DialogAddProductBinding
import com.example.market.presentation.ui.dialogs.add.vm.AddProductDialogViewModel
import com.example.market.utils.makeToast
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

    private var isListAdded = false
    val list = mutableListOf<String>()
    val listType = listOf("KG","M","L")

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

//        getAllCategories(0)

    }

    private fun initVariables() {
        lifecycleScope.launch {
            viewModel.getAllCategories()
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("nameCategory")?.observe(viewLifecycleOwner) {result ->
            makeToast(result)
            category = result
            binding.dropdownCategory.setText(result)
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("imageUrl")?.observe(viewLifecycleOwner) {result ->
            imageUrl = result
            makeToast(result)
        }
    }

    private fun initObservables() {
        viewModel.getCategoriesFlow.onEach {
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

        viewModel.addProductFlow.onEach {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            dismiss()
        }.launchIn(lifecycleScope)
    }

    private fun initListeners() {

//        binding.dropdownCategory.setOnClickListener {
//            findNavController().navigate(AddProductDialogDirections.actionAddProductDialog2ToCategoryDialog())
//        }

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
                makeToast("Заполните все поля!")
                Log.d("DSA",name)
                Log.d("DSA",amount)
                Log.d("DSA",price)
                Log.d("DSA",type)
                Log.d("DSA",category)
                Log.d("DSA",imageUrl)
            }
        }

        binding.btnCancel.setOnClickListener {
            dialog!!.dismiss()
        }

        binding.btnAddProductImage.setOnClickListener {
            findNavController().navigate(AddProductDialogDirections.actionAddProductDialog2ToImagesFragment())
        }

        binding.dropdownCategory.setOnItemClickListener { adapterView, view, i, l ->
            val item = adapterView.getItemAtPosition(i).toString()
            category = list[i]
//            getAllCategories(i)
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

    private fun getAllCategories(int:Int) {



    }
}