package com.osprey.scene.ui.topPager.taptorun

import com.osprey.core.base.BaseFragment
import com.osprey.scene.R
import com.osprey.scene.databinding.FragmentTapToRunLayoutBinding
import com.osprey.scene.ui.scene.SceneViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TapToRunFragment : BaseFragment<FragmentTapToRunLayoutBinding, SceneViewModel>(
    SceneViewModel::class) {

    override fun layoutId(): Int = R.layout.fragment_tap_to_run_layout

    override fun initialize() {
        setupUI()
        setupAction()
        observe()
    }

    private fun setupUI() {
        binding.lifecycleOwner = this

    }

    private fun setupAction() {
        binding.apply {

        }
    }

    private fun observe() {

    }

    companion object {

        fun newInstance() = TapToRunFragment()

    }
}