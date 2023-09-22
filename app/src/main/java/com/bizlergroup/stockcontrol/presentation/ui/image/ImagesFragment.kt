package com.bizlergroup.stockcontrol.presentation.ui.image

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bizlergroup.stockcontrol.R
import com.bizlergroup.stockcontrol.databinding.FragmentImagesBinding
import com.bizlergroup.stockcontrol.presentation.ui.image.adapter.RvImagesAdapter
import com.bizlergroup.stockcontrol.presentation.ui.image.vm.ImagesFragmentViewModel
import com.bizlergroup.stockcontrol.utils.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImagesFragment:DialogFragment(R.layout.fragment_images) {
    private lateinit var binding : FragmentImagesBinding
    private var adapter = RvImagesAdapter()
    private val viewModel : ImagesFragmentViewModel by viewModels()

    override fun getTheme(): Int = R.style.DialogTheme

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImagesBinding.bind(view)

        initVariables()

        initObservables()

        initListeners()
    }

    private fun initListeners() {

        adapter.onClickImage {
            findNavController().previousBackStackEntry?.savedStateHandle?.set(
                "imageUrl",
                it.imageUrl
            )
            findNavController().popBackStack()
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            }
            )
    }

    private fun initObservables() {
        viewModel.getAllImagesFlow.onEach {
            if (it.isEmpty()){
                binding.tvNoPhoto.visibility = View.VISIBLE
            }else{
                binding.tvNoPhoto.visibility = View.GONE
            }
            adapter.submitList(it)

        }.launchIn(lifecycleScope)

        viewModel.addImage.onEach {
            makeToast(it.message)
        }.launchIn(lifecycleScope)
    }

    private fun initVariables() {
        binding.rvImages.adapter = adapter

        lifecycleScope.launch {
            viewModel.getAllImages()
        }
    }
}