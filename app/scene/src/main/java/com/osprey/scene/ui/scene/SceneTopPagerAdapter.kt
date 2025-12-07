package com.osprey.scene.ui.scene

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.osprey.scene.ui.topPager.automation.AutomationFragment
import com.osprey.scene.ui.topPager.taptorun.TapToRunFragment

class SceneTopPagerAdapter(
    val fragmentActivity: FragmentActivity,
) : FragmentStateAdapter(fragmentActivity) {
    private var pages = ArrayList<Fragment>()

    init {
        pages.add(AutomationFragment.newInstance())
        pages.add(TapToRunFragment.newInstance())
    }

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun createFragment(position: Int): Fragment {
        return pages[position]
    }

}