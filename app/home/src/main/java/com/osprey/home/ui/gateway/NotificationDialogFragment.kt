package com.osprey.home.ui.gateway

import com.osprey.common.navigation.HomeNavigator
import com.osprey.core.base.fragment.BaseDialogFragment
import com.osprey.core.extension.debounceClick
import com.osprey.home.databinding.FragmentNotificationGatewayDialogBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class NotificationDialogFragment :
    BaseDialogFragment<FragmentNotificationGatewayDialogBinding, NotificationViewModel>(
        NotificationViewModel::class) {

    @Inject
    lateinit var homeNavigator: HomeNavigator

    override fun layoutId(): Int = com.osprey.home.R.layout.fragment_notification_gateway_dialog
    override fun initialize() {
        setupUi()
        setupAction()
    }

    private fun setupUi() {

    }

    private fun setupAction() {
        binding.apply {
            tvConfigure.debounceClick {
                homeNavigator.openResetDevice(requireContext())
            }
        }
    }

    companion object {

        fun newInstance() = NotificationDialogFragment()
    }
}