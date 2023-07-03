package com.example.market.presentation.ui.HomeScreen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.data.models.AddCategoryRequestData
import com.example.market.databinding.FragmentHomeBinding
import com.example.market.presentation.ui.MainFragment
import com.example.market.presentation.vm.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private var adapter = HomeScreenAdapter()
//    private var shimmerAdapter = ShimmerLayoutAdapter(5)
    private val viewModel : HomeFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        viewModel.getAllCategories()
//        binding.rvHome.adapter = shimmerAdapter

//        lifecycleScope.launch {
//            viewModel.addCategory(AddCategoryRequestData("Armatura","https://api-warehousing.up.railway.app/api/v1/images/1b8cc1a6-0828-4154-82ce-e6d5a111881b"))
//            delay(2000)
//            viewModel.addCategory(AddCategoryRequestData("Agash","https://api-warehousing.up.railway.app/api/v1/images/12d2521b-579f-4caa-831e-91c69a214c70"))
//            delay(2000)
//            viewModel.addCategory(AddCategoryRequestData("Truba","https://api-warehousing.up.railway.app/api/v1/images/92cb2a4b-1fd8-4702-bd53-0ac24598dfed"))
//        }

//        viewModel.addCategoryFlow.onEach {
//            viewModel.getAllCategories()
//        }.launchIn(lifecycleScope)

        viewModel.getAllCategoriesFlow.onEach {
            binding.rvHome.adapter = adapter
            adapter.submitList(it)
            binding.shimmerHome.stopShimmer()
            binding.shimmerHome.visibility = View.GONE
        }.launchIn(lifecycleScope)

        viewModel.messageGetAllCategoriesFlow.onEach {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)

        viewModel.errorGetAllCategoriesFlow.onEach {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)

        initVariables()
        initListeners()

    }

    private fun initListeners() {
        drawerLayoutListener()

        binding.btnMenu.setOnClickListener {
            binding.drawerLayout.open()
            MainFragment.BottomNavigationViewVisibilityLiveData.setVisibility(View.GONE)
            MainFragment.FloatActionButtonVisibilityLiveData.setVisibility(View.GONE)
        }
    }

    private fun initVariables() {
    }

    private fun drawerLayoutListener() {
        binding.drawerLayout.addDrawerListener(object :DrawerListener{
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

            }

            override fun onDrawerOpened(drawerView: View) {

            }

            override fun onDrawerClosed(drawerView: View) {
                MainFragment.BottomNavigationViewVisibilityLiveData.setVisibility(View.VISIBLE)
                MainFragment.FloatActionButtonVisibilityLiveData.setVisibility(View.VISIBLE)
            }

            override fun onDrawerStateChanged(newState: Int) {

            }

        })
        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.fragment_statistics -> {
                    findNavController().navigate(HomeFragmentDirections.actionFragmentHomeToFragmentStatistics())
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
            true
        }
    }


    //        DropDown settings
////        val items = listOf("Armatura", "Agash", "Shrup")
////        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_dropdown_menu, items)
////        binding.dropdownCategory.setAdapter(adapter)
}