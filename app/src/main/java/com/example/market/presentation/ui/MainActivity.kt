package com.example.market.presentation.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.market.R
import com.example.market.databinding.ActivityMainBinding
import com.example.market.utils.ConnectivityObserver
import com.example.market.utils.NetworkConnectivityObserver
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        checkInternetConnection()

        connectivityObserver.observe().onEach {
//            when(it){
//                ConnectivityObserver.StatusObserve.Available ->{
//                    showNoInternetDialog()
//                }
//                ConnectivityObserver.StatusObserve.Unavailable ->{
//                    showNoInternetDialog()
//                }
//                ConnectivityObserver.StatusObserve.Lost->{
//                    showNoInternetDialog()
//                }
//                ConnectivityObserver.StatusObserve.Losing->{
//                    showNoInternetDialog()
//                }
//            }
        }.launchIn(lifecycleScope)
    }

    private fun checkInternetConnection() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)

        if (capabilities != null &&
            (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
        ) {
            // Интернет доступен
            // Здесь можно выполнить необходимые действия, которые требуют интернета
        } else {
            // Интернет недоступен
            showNoInternetDialog()
        }
    }

    private fun showNoInternetDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Ошибка")
        dialogBuilder.setMessage("Отсутствует подключение к интернету. Проверьте свои настройки сети.")
        dialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        dialogBuilder.setCancelable(false)
        val dialog = dialogBuilder.create()
        dialog.show()
    }
}