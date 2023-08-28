package com.example.market.presentation.ui.home.screen

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.bold
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.market.R
import com.example.market.data.models.CategoryResponseData
import com.example.market.data.models.ProductResponseData
import com.example.market.data.models.SellProductRequestData
import com.example.market.databinding.FragmentHomeBinding
import com.example.market.presentation.ui.main.MainFragment
import com.example.market.presentation.ui.home.screen.adapter.HomeScreenAdapter
import com.example.market.utils.makeToast
import com.example.market.presentation.ui.home.screen.vm.HomeFragmentViewModel
import com.example.market.presentation.ui.stock.vm.StockFragmentViewModel
import com.example.market.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private var adapter = HomeScreenAdapter()
    private val viewModel: HomeFragmentViewModel by viewModels()
    private val viewModel2: StockFragmentViewModel by viewModels()

    private val listCategories  = mutableListOf<CategoryResponseData>()
    private val listProducts  = mutableListOf<ProductResponseData>()

    private var categoryId:Int?=null
    private var pressedProduct = ""

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        lifecycleScope.launch {
//            viewModel.getAllCategories()
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//
//        lifecycleScope.launch {
//            viewModel.getAllCategories()
//        }
//        categoryId?.let {
//            lifecycleScope.launch {
//                viewModel2.getAllProductByCategory(it)
//            }
//        }
//
//        val adapterCategory =
//            ArrayAdapter(requireContext(), R.layout.list_item_dropdown_menu, listCategories.map { it.name })
//        binding.dropdownCategory.setAdapter(adapterCategory)
//
//        val adapterProducts =
//            ArrayAdapter(requireContext(), R.layout.list_item_dropdown_menu, listProducts.map { it.name })
//        binding.dropdownProducts.setAdapter(adapterProducts)
//
//
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        //Проверка
        Log.d("JJJ", "Home token ${SharedPref.pref.getString("token","").toString()}")
        Log.d("JJJ", "Home login ${SharedPref.pref.getBoolean("isLogin",false).toString()}")

        initVariables()
        initObservables()
        initListeners()

    }

    private fun initObservables() {

        viewModel.getStatisticsFlow.onEach {
            binding.tvIncome.text = formatNumberWithThousandsSeparator(it.greenSum)
            binding.tvExpense.text = formatNumberWithThousandsSeparator(it.redSum)
        }.launchIn(lifecycleScope)

        viewModel.getAllCategoriesFlow.onEach {
            if (it.isEmpty()) {
                binding.tvNoCategory.visibility = View.VISIBLE
            } else {
                binding.tvNoCategory.visibility = View.GONE
            }
            binding.rvHome.adapter = adapter
            adapter.submitList(it)
            binding.shimmerHome.stopShimmer()
            binding.shimmerHome.visibility = View.GONE

            listCategories.clear()
            listCategories.addAll(it)

            val adapterCategory =
                ArrayAdapter(requireContext(), R.layout.list_item_dropdown_menu, listCategories.map { it.name })
            binding.dropdownCategory.setAdapter(adapterCategory)
            adapterCategory.notifyDataSetChanged()
        }.launchIn(lifecycleScope)

        viewModel2.getAllProduct.onEach {
            listProducts.clear()
            listProducts.addAll(it)

            val adapterProducts =
                ArrayAdapter(requireContext(), R.layout.list_item_dropdown_menu, listProducts.map { it.name })
            binding.dropdownProducts.setAdapter(adapterProducts)
            adapterProducts.notifyDataSetChanged()
        }.launchIn(lifecycleScope)

        viewModel2.messageGetAllProduct.onEach {
            makeToast(it)
        }.launchIn(lifecycleScope)

        viewModel2.errorGetAllProduct.onEach {
            makeToast(it.toString())
        }.launchIn(lifecycleScope)

        viewModel.sellProductFlow.onEach {
            makeToast(it.message)
        }.launchIn(lifecycleScope)

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("nameProduct")?.observe(viewLifecycleOwner) {result ->
            makeToast(result)
            binding.dropdownProducts.setText(result)
            pressedProduct = result
        }
    }

    private fun initListeners() {
        drawerLayoutListener()

        binding.dropdownCategory.setOnItemClickListener { adapterView, view, i, l ->
            val item = adapterView.getItemAtPosition(i).toString()
            val selectedCategory = listCategories[i]
            categoryId = selectedCategory.id


            lifecycleScope.launch {
                categoryId?.let {
                    viewModel2.getAllProductByCategory(it)
                }
            }
        }

        binding.dropdownProducts.setOnItemClickListener { adapterView, view, i, l ->
            val item = adapterView.getItemAtPosition(i).toString()
            pressedProduct = listProducts[i].name
        }

        binding.swipeRefreshHome.setOnRefreshListener {
            viewModel.getAllCategories()
            viewModel.getStatistics()
            binding.swipeRefreshHome.isRefreshing = false
        }

        binding.btnReset.setOnClickListener {
            binding.apply {
                dropdownCategory.setText("")
                dropdownProducts.setText("")
                etAmount.setText("")
            }

        }

//        binding.dropdownProducts.setOnClickListener {
//            findNavController().navigate(HomeFragmentDirections.actionFragmentHomeToProductsDialog())
//        }

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
            viewModel.getStatistics()
        }

        val textNoCategory = SpannableStringBuilder()
            .append("У вас нет категориев \n Категорию можно добавить в разделе\n ")
            .bold { append("Склад") }
        binding.tvNoCategory.text = textNoCategory
    }

    private fun drawerLayoutListener() {
        val header = binding.navView.getHeaderView(0)
        val name = header.findViewById<TextView>(R.id.tv_header_name)
        val surname = header.findViewById<TextView>(R.id.tv_header_surname)

        name.text = SharedPref.pref.getString("name","Name")
        surname.text = SharedPref.pref.getString("surname","Surname")
        

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
//                R.id.fragment_monitoring -> {
//                    binding.drawerLayout.closeDrawer(GravityCompat.START)
//                    findNavController().navigate(HomeFragmentDirections.actionFragmentHomeToMonitoringFragment())
//                }
                R.id.fragment_settings ->{
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    findNavController().navigate(HomeFragmentDirections.actionFragmentHomeToSettingFragment())
                }
            }
            true
        }
    }

    fun formatNumberWithThousandsSeparator(number: Int): String {
        val numberFormat: NumberFormat = DecimalFormat("#,###", DecimalFormatSymbols(Locale.getDefault()))
        return numberFormat.format(number)
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

