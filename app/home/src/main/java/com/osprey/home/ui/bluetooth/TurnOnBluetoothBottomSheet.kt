package com.osprey.home.ui.bluetooth

import com.osprey.core.base.fragment.BaseBottomSheetDialogFragment
import com.osprey.core.extension.dialogWidth
import com.osprey.home.databinding.TurnOnBottomSheetFragmentLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import com.osprey.home.R
import android.view.ViewGroup.LayoutParams

@AndroidEntryPoint
class TurnOnBluetoothBottomSheet :
    BaseBottomSheetDialogFragment<TurnOnBottomSheetFragmentLayoutBinding, TurnOnBluetoothViewModel>(
        TurnOnBluetoothViewModel::class) {

    interface OnTurnOnBluetoothListener {
        fun onDismiss()

        fun onTurnOn()
    }

    var listener: OnTurnOnBluetoothListener? = null

    override fun layoutId(): Int = R.layout.turn_on_bottom_sheet_fragment_layout

    override fun initialize() {
        binding.lifecycleOwner = viewLifecycleOwner
        setSize(
            requireActivity().dialogWidth,
            LayoutParams.MATCH_PARENT
        )
        setupUI()
        setupAction()
    }

    private fun setupUI(){

    }

    private fun setupAction(){

    }

    companion object {

        fun newInstance() = TurnOnBluetoothBottomSheet()
    }
}
