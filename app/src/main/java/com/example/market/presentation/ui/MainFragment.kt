package com.example.market.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.market.R
import com.example.market.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var childNavController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        binding.bottomNavigationView.menu.findItem(R.id.fragment_statistics).isChecked = false

        childNavController =
            (childFragmentManager.findFragmentById(R.id.fragmentContainerMain)as NavHostFragment).navController

        binding.bottomNavigationView.setupWithNavController(childNavController)

        binding.btnAddProduct.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddProductDialog2())
        }

        initVariables()
        initListeners()
    }

    private fun initListeners() {
        BottomNavigationViewVisibilityLiveData.observe(viewLifecycleOwner){
            binding.bottomAppBar.visibility = it
        }
        FloatActionButtonVisibilityLiveData.observe(viewLifecycleOwner){
            binding.btnAddProduct.visibility = it
        }

//        requireActivity().onBackPressedDispatcher
//            .addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true){
//                override fun handleOnBackPressed() {
//                    binding.bottomAppBar.visibility = View.VISIBLE
//                }
//            })
    }

    private fun initVariables() {
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(1).isEnabled = false
    }

    object BottomNavigationViewVisibilityLiveData : MutableLiveData<Int>() {
        fun setVisibility(visibility: Int) {
            value = visibility
        }
    }
    object FloatActionButtonVisibilityLiveData : MutableLiveData<Int>() {
        fun setVisibility(visibility: Int) {
            value = visibility
        }
    }
    }