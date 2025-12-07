package com.osprey.intro.ui.intro.tab

import android.os.Bundle
import com.bumptech.glide.Glide
import com.osprey.core.base.BaseFragment
import com.osprey.intro.databinding.FragmentIntroBinding
import com.osprey.core.extension.parcelable
import com.osprey.intro.BR
import com.osprey.intro.R
import com.osprey.intro.model.IntroData
import dagger.hilt.android.AndroidEntryPoint
import com.osprey.intro.ui.intro.IntroViewModel

@AndroidEntryPoint
class IntroFragment : BaseFragment<FragmentIntroBinding, IntroViewModel>(
    IntroViewModel::class
) {

    private val intro by lazy {
        arguments?.parcelable<IntroData>(KEY_INTRO)
    }

    override fun layoutId(): Int = R.layout.fragment_intro

    override fun initialize() {
        binding.setVariable(BR.intro, intro)
        intro?.bgResId?.let { resId ->
            Glide.with(requireContext())
                .load(resId)
                .into(binding.ivImage)
        }
    }

    companion object {
        private const val KEY_INTRO = "key_intro"

        @JvmStatic
        fun newInstance(intro: IntroData?) = IntroFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_INTRO, intro)
            }
        }
    }
}
