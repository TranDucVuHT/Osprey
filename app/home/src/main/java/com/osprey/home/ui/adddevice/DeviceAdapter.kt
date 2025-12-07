package com.osprey.home.ui.adddevice

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.osprey.core.viewmodel.ui.TMVVMAdapter
import com.osprey.core.viewmodel.ui.TMVVMViewHolder
import com.osprey.domain.home.model.local.Device_Profile
import com.osprey.home.databinding.ItemDeviceLayoutBinding
import com.osprey.home.BR

class DeviceAdapter(
    private val context: Context?,
    private val listener: OnItemClickListener
) : TMVVMAdapter<Device_Profile>(ArrayList()) {

    interface OnItemClickListener {
        fun onClick(item: Device_Profile)
    }

    override fun onCreateViewHolderBase(parent: ViewGroup?, viewType: Int): TMVVMViewHolder {
        val binding =
            ItemDeviceLayoutBinding.inflate(
                LayoutInflater.from(parent?.context),
                parent,
                false
            )
        return TMVVMViewHolder(binding)
    }

    override fun onBindViewHolderBase(holder: TMVVMViewHolder?, position: Int) {
        val device = list.getOrNull(position) ?: return
        val binding = holder?.binding as ItemDeviceLayoutBinding

        binding.setVariable(BR.device, device)
        binding.executePendingBindings()
    }
}
