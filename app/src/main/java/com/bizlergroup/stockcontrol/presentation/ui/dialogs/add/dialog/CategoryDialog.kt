package com.bizlergroup.stockcontrol.presentation.ui.dialogs.add.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bizlergroup.stockcontrol.R
import com.bizlergroup.stockcontrol.databinding.DialogCategoryBinding
import com.bizlergroup.stockcontrol.presentation.ui.dialogs.add.dialog.adapter.AdapterCategoryDialog
import com.bizlergroup.stockcontrol.presentation.ui.dialogs.add.vm.AddProductDialogViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryDialog:DialogFragment() {

    private lateinit var binding:DialogCategoryBinding
    private val viewModel : AddProductDialogViewModel by viewModels()
    private val adapter = AdapterCategoryDialog()

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.95).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.65).toInt()
        dialog!!.window?.setLayout(width, height)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_category,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogCategoryBinding.bind(view)

        initVariables()
        initObservables()
        initListeners()
    }

    private fun initVariables() {
        binding.recycleCategory.adapter = adapter
        lifecycleScope.launch {
            viewModel.getAllCategories()
        }
    }

    private fun initObservables() {
        viewModel.getCategoriesFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }

    private fun initListeners() {
        adapter.setOnItemClick {
            findNavController().previousBackStackEntry?.savedStateHandle?.set("nameCategory",it.name)
            findNavController().popBackStack()
        }
    }
}