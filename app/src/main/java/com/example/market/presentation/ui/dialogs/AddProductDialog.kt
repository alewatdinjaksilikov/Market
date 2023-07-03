package com.example.market.presentation.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.databinding.DialogAddProductBinding
import com.example.market.presentation.ui.ImagesScreen.ImagesFragment
import com.example.market.presentation.vm.ClickImageListenerViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddProductDialog:BottomSheetDialogFragment() {
    private lateinit var binding: DialogAddProductBinding
    private val viewModelClickImage : ClickImageListenerViewModel by viewModels()

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
        return inflater.inflate(R.layout.dialog_add_product,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogAddProductBinding.bind(view)

        binding.btnCancel.setOnClickListener {
            dialog!!.dismiss()
        }

        ImagesFragment().imageClicked.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "Mnawin keldi awaaaaa", Toast.LENGTH_SHORT).show()
        }

        binding.btnAddProductImage.setOnClickListener {
            findNavController().navigate(AddProductDialogDirections.actionAddProductDialog2ToImagesFragment())
        }

        val adapterCategory = ArrayAdapter<String>(requireContext(),R.layout.list_item_dropdown_menu,list)
        binding.dropdownCategory.setAdapter(adapterCategory)
        binding.dropdownCategory.setOnItemClickListener { adapterView, view, i, l ->
            val item = adapterView.getItemAtPosition(i).toString()
            Toast.makeText(requireContext(),"Item $item",Toast.LENGTH_SHORT).show()
        }

        val adapterType = ArrayAdapter<String>(requireContext(),R.layout.list_item_dropdown_menu,listType)
        binding.dropdownTypeProduct.setAdapter(adapterType)
        binding.dropdownTypeProduct.setOnItemClickListener { adapterView, view, i, l ->
            val item = adapterView.getItemAtPosition(i).toString()
            Toast.makeText(requireContext(),"Item $item",Toast.LENGTH_SHORT).show()
        }

    }
}