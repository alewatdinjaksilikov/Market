package com.example.market.presentation.ui.HomeScreen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.data.models.SellProductRequestData
import com.example.market.databinding.FragmentHomeBinding
import com.example.market.presentation.ui.MainFragment
import com.example.market.presentation.ui.MainFragmentDirections
import com.example.market.presentation.vm.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private var adapter = HomeScreenAdapter()
    private val viewModel : HomeFragmentViewModel by viewModels()
    private var pressedProduct = ""
    val list = mutableListOf<String>()

    override fun onStop() {
        super.onStop()
        list.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        list.clear()
    }

    override fun onPause() {
        super.onPause()
        list.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        initVariables()
        initObservables()
        initListeners()

    }

    private fun initObservables() {
        viewModel.getAllCategoriesFlow.onEach {
            binding.rvHome.adapter = adapter
            adapter.submitList(it)
            binding.shimmerHome.stopShimmer()
            binding.shimmerHome.visibility = View.GONE
        }.launchIn(lifecycleScope)

        viewModel.getAllProductsFlow.onEach {
            it.forEach { data ->
                list.add(data.name)
            }
            val adapterType = ArrayAdapter(requireContext(),R.layout.list_item_dropdown_menu,list)
            binding.dropdownProducts.setAdapter(adapterType)
        }.launchIn(lifecycleScope)

        viewModel.sellProductFlow.onEach {
            Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)
    }

    private fun initListeners() {
        drawerLayoutListener()

        binding.tvCategory.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionFragmentHomeToMonitoringFragment() )
        }

        adapter.setOnItemClick {
            findNavController().navigate(HomeFragmentDirections.actionFragmentHomeToListProductsDialog(it.id,it.name))
        }

        binding.btnSales.setOnClickListener {
            val amount = binding.btnEditAmount.text.toString()
            if (pressedProduct!="" && amount!=""){
                lifecycleScope.launch{
                    viewModel.sellProduct(
                        SellProductRequestData(
                            amount = amount.toInt(),
                            name = pressedProduct
                        )
                    )
                }
            }else{
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
            }

        }

        binding.dropdownProducts.setOnItemClickListener { adapterView, view, i, l ->
            val item = adapterView.getItemAtPosition(i).toString()
            pressedProduct = list[i]
            list.clear()
        }

        binding.btnMenu.setOnClickListener {
            binding.drawerLayout.open()
            MainFragment.BottomNavigationViewVisibilityLiveData.setVisibility(View.GONE)
            MainFragment.FloatActionButtonVisibilityLiveData.setVisibility(View.GONE)
        }
    }

    private fun initVariables() {
        list.clear()
        lifecycleScope.launch {
            viewModel.getAllCategories()
            viewModel.getAllProducts()
        }
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
                R.id.fragment_monitoring -> {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    val navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerMain)
                    navController.navigate(MainFragmentDirections.actionMainFragmentToFragmentMonitoring())
                }
            }
            true
        }
    }
}