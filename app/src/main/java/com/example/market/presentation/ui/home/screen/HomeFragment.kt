package com.example.market.presentation.ui.home.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.data.models.SellProductRequestData
import com.example.market.databinding.FragmentHomeBinding
import com.example.market.presentation.ui.main.MainFragment
import com.example.market.presentation.ui.home.screen.adapter.HomeScreenAdapter
import com.example.market.utils.makeToast
import com.example.market.presentation.ui.home.screen.vm.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private var adapter = HomeScreenAdapter()
    private val viewModel: HomeFragmentViewModel by viewModels()
    private var pressedProduct = ""

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


        viewModel.sellProductFlow.onEach {
            makeToast(it.message)
        }.launchIn(lifecycleScope)

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("nameProduct")?.observe(viewLifecycleOwner) {result ->
            // Do something with the result.
            makeToast(result)
            binding.dropdownProducts.setText(result)
            pressedProduct = result
        }
    }

    private fun initListeners() {
        drawerLayoutListener()

        binding.btnReset.setOnClickListener {
            binding.apply {
                dropdownProducts.setText("")
                etAmount.setText("")
                dropdownTypeProduct.setText("")
            }

        }

        binding.dropdownProducts.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionFragmentHomeToProductsDialog())
        }

        adapter.setOnItemClick {
            findNavController().navigate(HomeFragmentDirections.actionFragmentHomeToListProductsDialog(it.id,it.name))
        }

        binding.btnSales.setOnClickListener {
            val amount = binding.etAmount.text.toString()
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

        binding.btnMenu.setOnClickListener {
            binding.drawerLayout.open()
            MainFragment.BottomNavigationViewVisibilityLiveData.setVisibility(View.GONE)
            MainFragment.FloatActionButtonVisibilityLiveData.setVisibility(View.GONE)
        }
    }

    private fun initVariables() {
        lifecycleScope.launch {
            viewModel.getAllCategories()
        }
    }

    private fun drawerLayoutListener() {
        binding.drawerLayout.addDrawerListener(object :DrawerListener{
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                MainFragment.BottomNavigationViewVisibilityLiveData.setVisibility(View.GONE)
                MainFragment.FloatActionButtonVisibilityLiveData.setVisibility(View.GONE)
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
                    findNavController().navigate(HomeFragmentDirections.actionFragmentHomeToMonitoringFragment())
                }
            }
            true
        }
    }
}



//    private var isListAdded = false
//
//    val list = mutableListOf<String>()

//        viewModel.getAllProductsFlow.onEach {
//            if (!isListAdded){
//                it.forEach { data ->
//                    list.add(data.name)
//                }
//                isListAdded = true
//            }
//
//        }.launchIn(lifecycleScope)
//        val adapterType = ArrayAdapter(requireContext(),R.layout.list_item_dropdown_menu,list)
//        binding.dropdownProducts.setAdapter(adapterType)

//        binding.dropdownProducts.setOnItemClickListener { adapterView, view, i, l ->
//            val item = adapterView.getItemAtPosition(i).toString()
//            pressedProduct = list[i]
//        }