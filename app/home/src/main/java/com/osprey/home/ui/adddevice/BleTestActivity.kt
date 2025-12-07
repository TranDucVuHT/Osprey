package com.osprey.home.ui.adddevice

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.*
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat

class BleTestActivity : ComponentActivity() {
    private var bluetoothAdapter: BluetoothAdapter? = null
    private val bleScanner by lazy { bluetoothAdapter?.bluetoothLeScanner }
    private var mScanCallback: ScanCallback? = null
    private var deviceCount = 0
    private var connectedGatt: BluetoothGatt? = null
    private val handler = Handler(Looper.getMainLooper())

    private val permissions = mutableListOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ).apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            add(Manifest.permission.BLUETOOTH_SCAN)
            add(Manifest.permission.BLUETOOTH_CONNECT)
        }
    }.toTypedArray()

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
            if (results.values.all { it }) {
                checkLocationAndScan()
            } else {
                Log.e("BLE", "❌ Permission denied: $results")
            }
        }

    private val enableBluetoothLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (bluetoothAdapter?.isEnabled == true) {
                checkLocationAndScan()
            } else {
                Log.e("BLE", "❌ Bluetooth not enabled")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("BLE", "🚀 Activity started")

        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter

        if (bluetoothAdapter == null) {
            Log.e("BLE", "❌ Device doesn't support Bluetooth")
            return
        }

        initScanCallback()
        checkPermissionAndScan()
    }

    private fun initScanCallback() {
        mScanCallback = object : ScanCallback() {

            @SuppressLint("MissingPermission")
            override fun onScanResult(callbackType: Int, result: ScanResult) {
                deviceCount++

                val device = result.device
                val name = device.name ?: "[No Name]"
                val mac = device.address
                val rssi = result.rssi

                Log.i("BLE_DEVICE", "📱 Device #$deviceCount: $name | MAC: $mac | RSSI: $rssi dBm")
                result.scanRecord?.let { record ->
                    Log.d("BLE_DEVICE", "  -> Service UUIDs: ${record.serviceUuids}")
                    Log.d("BLE_DEVICE", "  -> Device Name: ${record.deviceName}")
                    Log.d("BLE_DEVICE", "  -> Manufacturer: ${record.manufacturerSpecificData}")
                }

                if (name.contains("OSPREY", ignoreCase = true)) {
                    Log.e("BLE", "🎯 OSPREY DETECTED! Auto connecting: $mac")
                    stopScan()
                    connectToDevice(mac)
                }
            }
            override fun onScanFailed(errorCode: Int) {
                Log.e("BLE", "❌ Scan FAILED: $errorCode")
            }
        }
    }


    private fun checkPermissionAndScan() {
        val missing = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (missing.isEmpty()) {
            checkBluetoothAndScan()
        } else {
            requestPermission.launch(permissions)
        }
    }

    private fun checkBluetoothAndScan() {
        if (bluetoothAdapter?.isEnabled != true) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            enableBluetoothLauncher.launch(enableBtIntent)
        } else {
            checkLocationAndScan()
        }
    }

    private fun checkLocationAndScan() {
        if (!isLocationEnabled()) {
            Log.e("BLE", "❌ Location service OFF")
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
            return
        }
        startScan()
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    @SuppressLint("MissingPermission")
    private fun startScan() {
        deviceCount = 0

        val settings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .build()

        Log.d("BLE", "🔍 SCANNING…")

        bleScanner?.startScan(null, settings, mScanCallback)
    }

    @SuppressLint("MissingPermission")
    private fun stopScan() {
        try {
            mScanCallback?.let { bleScanner?.stopScan(it) }
        } catch (_: Exception) { }
        Log.d("BLE", "🛑 Scan stopped")
    }

    @SuppressLint("MissingPermission")
    private fun connectToDevice(mac: String) {
        val device = bluetoothAdapter?.getRemoteDevice(mac)

        if (device == null) {
            Log.e("GATT", "❌ Device null for MAC: $mac")
            return
        }

        Log.i("GATT", "🔌 Connecting to $mac ...")

        connectedGatt = device.connectGatt(this, false, object : BluetoothGattCallback() {

            override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
                when (newState) {
                    BluetoothProfile.STATE_CONNECTED -> {
                        Log.i("GATT", "✅ CONNECTED to $mac")
                        handler.post { gatt.discoverServices() }
                    }

                    BluetoothProfile.STATE_DISCONNECTED -> {
                        Log.e("GATT", "❌ DISCONNECTED (status=$status)")
                        connectedGatt = null
                    }
                }
            }

            override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
                if (status == BluetoothGatt.GATT_SUCCESS) {
                    Log.i("GATT", "📡 SERVICES DISCOVERED:")
                    gatt.services.forEach { service ->
                        Log.d("GATT", "→ Service: ${service.uuid}")

                        service.characteristics.forEach { c ->
                            Log.d("GATT", "    → Characteristic: ${c.uuid}")
                        }
                    }
                } else {
                    Log.e("GATT", "❌ Service discovery failed: $status")
                }
            }

            override fun onCharacteristicRead(
                gatt: BluetoothGatt,
                characteristic: BluetoothGattCharacteristic,
                status: Int
            ) {
                Log.i("GATT", "📥 Read from ${characteristic.uuid} = ${characteristic.value?.contentToString()}")
            }

            override fun onCharacteristicWrite(
                gatt: BluetoothGatt,
                characteristic: BluetoothGattCharacteristic,
                status: Int
            ) {
                Log.i("GATT", "📤 Write to ${characteristic.uuid} -> status $status")
            }

            override fun onCharacteristicChanged(
                gatt: BluetoothGatt,
                characteristic: BluetoothGattCharacteristic
            ) {
                Log.i("GATT", "🔔 NOTIFY ${characteristic.uuid} = ${characteristic.value?.contentToString()}")
            }
        })
    }


    @SuppressLint("MissingPermission")
    private fun writeData(uuidService: String, uuidChar: String, data: ByteArray) {
        val gatt = connectedGatt ?: return
        val service = gatt.getService(java.util.UUID.fromString(uuidService)) ?: return
        val characteristic = service.getCharacteristic(java.util.UUID.fromString(uuidChar)) ?: return

        characteristic.value = data
        gatt.writeCharacteristic(characteristic)
    }

    @SuppressLint("MissingPermission")
    private fun readData(uuidService: String, uuidChar: String) {
        val gatt = connectedGatt ?: return
        val service = gatt.getService(java.util.UUID.fromString(uuidService)) ?: return
        val characteristic = service.getCharacteristic(java.util.UUID.fromString(uuidChar)) ?: return

        gatt.readCharacteristic(characteristic)
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    override fun onDestroy() {
        super.onDestroy()
        stopScan()
        connectedGatt?.close()
        connectedGatt = null
    }
}
