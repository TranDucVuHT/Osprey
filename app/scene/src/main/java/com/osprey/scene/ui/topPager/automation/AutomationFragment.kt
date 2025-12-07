package com.osprey.scene.ui.topPager.automation

import com.osprey.core.base.BaseFragment
import com.osprey.scene.R
import com.osprey.scene.databinding.FragmentAutomationLayoutBinding
import com.osprey.scene.ui.scene.SceneViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AutomationFragment: BaseFragment<FragmentAutomationLayoutBinding, SceneViewModel>(
    SceneViewModel::class) {

    override fun layoutId(): Int = R.layout.fragment_automation_layout

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

        fun newInstance() = AutomationFragment()

    }
}