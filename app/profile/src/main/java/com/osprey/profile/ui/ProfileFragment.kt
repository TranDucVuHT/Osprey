package com.osprey.profile.ui


import dagger.hilt.android.AndroidEntryPoint
import com.osprey.profile.R
import com.osprey.core.base.BaseFragment
import com.osprey.profile.databinding.FragmentProfileLayoutBinding

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileLayoutBinding, ProfileViewModel>(
    ProfileViewModel::class) {
    override fun layoutId(): Int = R.layout.fragment_profile_layout

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
        fun newInstance() = ProfileFragment()
    }
}