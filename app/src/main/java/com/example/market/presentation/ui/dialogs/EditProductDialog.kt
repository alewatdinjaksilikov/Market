package com.example.market.presentation.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.market.R
import com.example.market.databinding.DialogEditProductBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProductDialog:BottomSheetDialogFragment() {
    private lateinit var binding: DialogEditProductBinding

    val list = listOf("Armatura","Agash","Shrup","Armatura d10")
    val listType = listOf("Kg","Litr","Mm","Metr")

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
        val adapterCategory = ArrayAdapter<String>(requireContext(),R.layout.list_item_dropdown_menu,list)
        binding.dropdownCategory.setAdapter(adapterCategory)
        binding.dropdownCategory.setOnItemClickListener { adapterView, view, i, l ->
            val item = adapterView.getItemAtPosition(i).toString()
            Toast.makeText(requireContext(),"Item $item", Toast.LENGTH_SHORT).show()
        }

        val adapterType = ArrayAdapter<String>(requireContext(),R.layout.list_item_dropdown_menu,listType)
        binding.dropdownTypeProduct.setAdapter(adapterType)
        binding.dropdownTypeProduct.setOnItemClickListener { adapterView, view, i, l ->
            val item = adapterView.getItemAtPosition(i).toString()
            Toast.makeText(requireContext(),"Item $item", Toast.LENGTH_SHORT).show()
        }

    }
}