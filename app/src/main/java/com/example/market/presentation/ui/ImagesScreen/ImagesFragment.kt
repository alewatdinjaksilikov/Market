package com.example.market.presentation.ui.ImagesScreen

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.databinding.FragmentImagesBinding
import com.example.market.presentation.ui.ImagesScreen.adapter.RvImagesAdapter
import com.example.market.presentation.vm.ClickImageListenerViewModel
import com.example.market.presentation.vm.ImagesFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImagesFragment:DialogFragment(R.layout.fragment_images) {
    private lateinit var binding : FragmentImagesBinding
    private var adapter = RvImagesAdapter()
    private val viewModel : ImagesFragmentViewModel by viewModels()
    private val viewModelClickImage : ClickImageListenerViewModel by viewModels()
    private val _imageClicked = MutableLiveData<String>()
    val imageClicked: LiveData<String> get() = _imageClicked

    override fun getTheme(): Int = R.style.DialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(requireContext(),theme){
            override fun onBackPressed() {
                    Toast.makeText(requireContext(), "Выберите один из этих фотографий!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImagesBinding.bind(view)

        binding.rvImages.adapter = adapter

        lifecycleScope.launch {
            viewModel.getAllImages()
        }

        viewModel.getAllImagesFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        adapter.onClickImage {
            Toast.makeText(requireContext(), "Basildi", Toast.LENGTH_SHORT).show()
            _imageClicked.value = it.imageUrl
            Log.d("AAA",it.imageUrl)
            findNavController().popBackStack()
        }
    }

}