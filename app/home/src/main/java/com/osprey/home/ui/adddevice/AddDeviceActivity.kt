package com.osprey.home.ui.adddevice

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.osprey.common.navigation.HomeNavigator
import com.osprey.core.base.activity.BaseActivity
import com.osprey.core.extension.*
import com.osprey.domain.home.model.local.Device_Profile
import com.osprey.home.R
import com.osprey.home.databinding.ActivityAddDeviceLayoutBinding
import com.osprey.home.ui.bluetooth.TurnOnBluetoothBottomSheet
import com.osprey.home.ui.gateway.NotificationDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddDeviceActivity : BaseActivity<ActivityAddDeviceLayoutBinding, AddDevicViewModel>(
    AddDevicViewModel::class) {

    @Inject
    lateinit var homeNavigator: HomeNavigator

    private var bluetoothAdapter: BluetoothAdapter? = null
    private val bleScanner by lazy { bluetoothAdapter?.bluetoothLeScanner }

    private var isWifiReceiverRegistered = false

    private val deviceAdapter by lazy {
        DeviceAdapter(
            this@AddDeviceActivity,
            object : DeviceAdapter.OnItemClickListener {
                override fun onClick(item: Device_Profile) {}
            }
        )
    }

    private val permissions = mutableListOf(
        Manifest.permission.ACCESS_FINE_LOCATION
    ).apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            add(Manifest.permission.BLUETOOTH_SCAN)
            add(Manifest.permission.BLUETOOTH_CONNECT)
        }
    }.toTypedArray()

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            if (result.values.all { it }) {
                checkBluetoothAndScan()
            } else {
                toast("Permission denied!")
            }
        }

    private val enableBluetoothLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                toast("Bluetooth enabled!")
                startScanBle()
            } else {
                toast("Bluetooth not enabled")
            }
        }

    override fun layoutId(): Int = R.layout.activity_add_device_layout

    override fun initialize() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        setupUI()
        setupAction()
        setupRecycleView()
        updateUiState()
    }

    private fun setupUI() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val text = "Follow instructions and make the device ready for pairing"
        val colored = text.replace(
            "instructions",
            "<font color='${
                ContextCompat.getColor(this, com.osprey.core.R.color.colorPrimary).toHexColor()
            }'>instructions</font>"
        )
        binding.tvMessage.text = HtmlCompat.fromHtml(colored, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    private fun setupAction() {
        binding.llTurnOnBluetooth.debounceClick { showTurnOnBluetoothDialog() }
        binding.tvTryAgain.debounceClick { checkPermissionsAndScan() }
        binding.radarView.debounceClick { checkPermissionsAndScan() }

        binding.toolbar.ivScan.debounceClick {
            Log.d("AddDevice", "Scan icon clicked")

            val intent = Intent(this, BleTestActivity::class.java)
            startActivity(intent)
        }

        binding.toolbar.ivBack.debounceClick { finish() }

        binding.rvDevice.debounceClick {
            NotificationDialogFragment.newInstance()
                .showAllowingStateLoss(supportFragmentManager, "NotificationDialog")
        }

    }

    private fun setupRecycleView() {
        val spacing = resources.getDimensionPixelSize(com.osprey.core.R.dimen.dp_4)
        binding.rvDevice.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = deviceAdapter
            addSpaceDecoration(spacing, false)
        }
    }

    private fun checkPermissionsAndScan() {
        val missing = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
        if (missing.isEmpty()) checkBluetoothAndScan()
        else requestPermission.launch(permissions)
    }

    private fun checkBluetoothAndScan() {
        if (bluetoothAdapter == null) {
            toast("Device does not support Bluetooth")
            return
        }
        if (!bluetoothAdapter!!.isEnabled) {
            showTurnOnBluetoothDialog()
            return
        }
        startScanBle()
    }

    private fun showTurnOnBluetoothDialog() {
        val dialog = TurnOnBluetoothBottomSheet.newInstance()
        dialog.listener = object : TurnOnBluetoothBottomSheet.OnTurnOnBluetoothListener {
            override fun onDismiss() {}
            override fun onTurnOn() {
                val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                enableBluetoothLauncher.launch(enableIntent)
            }
        }
        dialog.showAllowingStateLoss(supportFragmentManager, "TurnOnBluetooth")
    }

    private fun startScanBle() {
        if (!hasBlePermissions()) {
            Log.e("BLE", "Missing permission -> stop scan")
            return
        }

        if (bluetoothAdapter?.isEnabled != true) return

        val settings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .build()

        try {
            bleScanner?.startScan(null, settings, bleScanCallback)
            Log.d("BLE", "Scanning with LOW_LATENCY...")
        } catch (e: SecurityException) {
            Log.e("BLE", "Scan permission error: ${e.message}")
        }
    }

    private fun hasBlePermissions(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) ==
                    PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
        }
    }

    private val bleScanCallback = object : ScanCallback() {
        override fun onScanResult(type: Int, result: ScanResult?) {
            try {
                if (!hasBlePermissions()) return
                val device = result?.device ?: return
                Log.d("BLE_DEVICE", "Found: name=${device.name ?: "null"}, mac=${device.address}")
            } catch (e: SecurityException) {
                Log.e("BLE_DEVICE", "Missing permission: ${e.message}")
            }
        }

        override fun onBatchScanResults(results: MutableList<ScanResult>?) {
            try {
                if (!hasBlePermissions()) return
                results?.forEach { res ->
                    Log.d(
                        "BLE_DEVICE",
                        "Batch: name=${res.device.name ?: "null"}, mac=${res.device.address}"
                    )
                }
            } catch (e: SecurityException) {
                Log.e("BLE_DEVICE", "Batch permission error: ${e.message}")
            }
        }

        override fun onScanFailed(errorCode: Int) {
            Log.e("BLE", "Scan failed: $errorCode")
        }
    }

    override fun onResume() {
        super.onResume()
        updateUiState()

        if (!isWifiReceiverRegistered) {
            registerReceiver(wifiReceiver, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))
            isWifiReceiverRegistered = true
        }
    }

    override fun onPause() {
        super.onPause()
        if (isWifiReceiverRegistered) {
            unregisterReceiver(wifiReceiver)
            isWifiReceiverRegistered = false
        }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_SCAN)
    override fun onDestroy() {
        super.onDestroy()
        try {
            bleScanner?.stopScan(bleScanCallback)
        } catch (_: Exception) {}
    }

    private fun isBluetoothOn() = bluetoothAdapter?.isEnabled == true

    private fun isWifiOn(): Boolean {
        val wifiManager = getSystemService(Context.WIFI_SERVICE) as WifiManager
        return wifiManager.isWifiEnabled
    }

    private fun updateUiState() {
        binding.llTurnOnBluetooth.setVisible(!isBluetoothOn())
        binding.llTurnOnWifi.setVisible(!isWifiOn())
    }

    private val wifiReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == WifiManager.WIFI_STATE_CHANGED_ACTION) updateUiState()
        }
    }
}
