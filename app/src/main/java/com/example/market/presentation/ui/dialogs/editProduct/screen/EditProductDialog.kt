package com.example.market.presentation.ui.dialogs.editProduct.screen

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
import com.example.market.data.models.EditProductRequestData
import com.example.market.databinding.DialogEditProductBinding
import com.example.market.presentation.ui.dialogs.add.vm.AddProductDialogViewModel
import com.example.market.presentation.ui.dialogs.editProduct.vm.EditProductDialogViewModel
import com.example.market.utils.EditProductClick
import com.example.market.utils.makeToast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditProductDialog:BottomSheetDialogFragment() {
    private lateinit var binding: DialogEditProductBinding
    private val viewModel : AddProductDialogViewModel by viewModels()
    private val viewModelEdit : EditProductDialogViewModel by viewModels()
    private val args : EditProductDialogArgs by navArgs()

    private var selectedCategoryId = 1
    private var imageUrl = ""
    private var type = ""
    private var category = ""

    private var isListAdded = false
    private val list = mutableListOf<String>()
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
        return inflater.inflate(R.layout.dialog_edit_product,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogEditProductBinding.bind(view)

        lifecycleScope.launch {
            viewModelEdit.getProductByName(name = args.name)
            viewModel.getAllCategories()
        }


        initObservables()
        initListeners()

        binding.dropdownCategory.setOnItemClickListener { adapterView, view, i, l ->
            val item = adapterView.getItemAtPosition(i).toString()
            category = list[i]
            selectedCategoryId = i
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

    private fun initListeners() {

        binding.btnEditProduct.setOnClickListener {
            Toast.makeText(requireContext(),"Clicked",Toast.LENGTH_SHORT).show()
            val name = binding.etProductName.text.toString()
            val amount = binding.etProductAmount.text.toString()
            val price = binding.etProductPrice.text.toString()

            if (name!=="" && amount!="" && price!="" && type!="" && category!="" && imageUrl!=""){
                lifecycleScope.launch{
                    viewModelEdit.editProduct(
                        args.id,
                        EditProductRequestData(
                            amount = amount.toInt(),
                            category = category,
                            imageUrl = imageUrl,
                            name = name,
                            price = price.toInt(),
                            unit = type
                        )
                    )
                    Log.d("YYY","Kirdi")
                }
            }else{
                makeToast("Заполните все поля!!!")
            }
        }

        binding.btnAddProductImage.setOnClickListener {
            findNavController().navigate(EditProductDialogDirections.actionEditProductDialogToImagesFragment2())
        }
    }

    private fun initObservables() {

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("imageUrl")?.observe(viewLifecycleOwner) {result ->
            imageUrl = result
        }

        viewModelEdit.getProductFlow.onEach {
            binding.apply {
                Log.d("YYY","${it.name} ${it.amount} ${it.price}")
                etProductName.setText(it.name)
                etProductAmount.setText(it.amount.toString())
                etProductPrice.setText(it.price.toString())
            }
        }.launchIn(lifecycleScope)

        viewModelEdit.editProductFlow.onEach {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            if (it.statusCode == 200){
                EditProductClick.buttonAddCategoryClick(true)
            }
            Log.d("YYY",it.message)
            dismiss()
        }.launchIn(lifecycleScope)

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
    }
}