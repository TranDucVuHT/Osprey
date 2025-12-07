// HomeFragment.kt
package com.osprey.home.ui.home

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.osprey.common.navigation.HomeNavigator
import com.osprey.core.base.BaseFragment
import com.osprey.core.extension.debounceClick
import com.osprey.core.extension.show
import com.osprey.home.R
import com.osprey.home.databinding.FragmentHomeLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeLayoutBinding, HomeViewModel>(
    HomeViewModel::class
) {

    @Inject
    lateinit var homeNavigator: HomeNavigator

    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
            viewModel.handleAction(HomeUiAction.LoadWeather)
        }
    }

    override fun layoutId(): Int = R.layout.fragment_home_layout

    override fun initialize() {
        setupUI()
        setupAction()
        observe()
        checkLocationPermissionAndLoadWeather()
    }

    private fun setupUI() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupAction() {
        binding.apply {
            tvAdd.debounceClick {
                homeNavigator.openAddDevice(requireContext())
            }
            cvWeather.show()
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.uiState.collect {
                binding.weatherData = it.weatherData

                it.weatherError?.let { error ->

                }
            }
        }
    }

    private fun checkLocationPermissionAndLoadWeather() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.handleAction(HomeUiAction.LoadWeather)
            }
            else -> {
                locationPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}