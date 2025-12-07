package com.osprey.scene.ui.scene

import com.osprey.common.navigation.HomeNavigator
import com.osprey.core.base.BaseFragment
import com.osprey.scene.databinding.FragmentSceneLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.osprey.scene.R
import com.osprey.scene.ui.menu.SceneBarMenu
import com.osprey.scene.ui.menu.SceneBarView

@AndroidEntryPoint
class SceneFragment : BaseFragment<FragmentSceneLayoutBinding, SceneViewModel>(
    SceneViewModel::class) {
    @Inject
    lateinit var homeNavigator: HomeNavigator

    private val sceneTopPagerAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SceneTopPagerAdapter(requireActivity())
    }

    override fun layoutId(): Int = R.layout.fragment_scene_layout

    override fun initialize() {
        setupUI()
        setupAction()
        observe()
        setupViewPagers()
        setupBottomBar()
        observeViewModel()
    }

    private fun setupUI() {
        binding.lifecycleOwner = this

    }

    private fun setupAction() {
        binding.apply {

        }
    }

    private fun setupViewPagers() {
        binding.viewPagerBottom.run {
            adapter = sceneTopPagerAdapter
            offscreenPageLimit = 1
            isUserInputEnabled = false
        }
    }

    private fun observe() {

    }


    private fun setupBottomBar() {
        binding.taskbarView.listener = object : SceneBarView.OnTopBarViewListener {
            override fun onClick(menu: SceneBarMenu) {
                when(menu.type){
                    SceneBarMenu.TopBarMenuType.AUTOMATION -> {
                        binding.viewPagerBottom.setCurrentItem(0, false)
                        viewModel.dispatch(SceneUiAction.ChangeTab(SceneBarMenu.TopBarMenuType.AUTOMATION))
                    }
                    SceneBarMenu.TopBarMenuType.TAPTORUN -> {
                        binding.viewPagerBottom.setCurrentItem(1, false)
                        viewModel.dispatch(SceneUiAction.ChangeTab(SceneBarMenu.TopBarMenuType.TAPTORUN))
                    }
                }
            }
        }
    }


    private fun observeViewModel() {
        viewModel.changePageBottomEvent.observe(this) { page ->
            binding.viewPagerBottom.setCurrentItem(page, false)
        }
    }

    companion object {
        fun newInstance() = SceneFragment()
    }
}