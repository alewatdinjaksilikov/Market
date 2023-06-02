package com.example.market.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.market.R
import com.example.market.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var conf: AppBarConfiguration
    private lateinit var drawerLayout : DrawerLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        initVariables()


    }

    private fun initVariables() {
        drawerLayout = binding.drawerLayout
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.includeContentMain.includeToolbar.mainToolbar)
        activity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        val navController =
            activity.findNavController(R.id.main_fragmentContainerView)
        conf = AppBarConfiguration(
            setOf(
                R.id.fragment_statistics,
                R.id.fragment_stock
            ), binding.drawerLayout
        )
        activity.setupActionBarWithNavController(navController, conf)
        binding.navView.setupWithNavController(navController)
        activity.onSupportNavigateUp()

        binding.includeContentMain.includeToolbar.mainToolbar.setNavigationOnClickListener {
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            drawerLayout.open()
        }
    }
}