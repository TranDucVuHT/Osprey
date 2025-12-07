package com.osprey.main.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.osprey.home.ui.home.HomeFragment
import com.osprey.mall.ui.MallFragment
import com.osprey.profile.ui.ProfileFragment
import com.osprey.scene.ui.scene.SceneFragment

class MainBottomPagerAdapter(
    val fragmentActivity: FragmentActivity,
) : FragmentStateAdapter(fragmentActivity) {
    private var pages = ArrayList<Fragment>()

    init {
        pages.add(HomeFragment.newInstance())
        pages.add(SceneFragment.newInstance())
        pages.add(MallFragment.newInstance())
        pages.add(ProfileFragment.newInstance())
    }

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun createFragment(position: Int): Fragment {
        return pages[position]
    }

}
