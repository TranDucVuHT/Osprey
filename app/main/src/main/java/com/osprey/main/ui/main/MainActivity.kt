package com.osprey.main.ui.main

//import com.starnest.core.data.model.event.LanguageEvent
//import com.starnest.core.extension.changeLanguage
import android.content.Intent
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.osprey.common.navigation.MainNavigator
import com.osprey.common.ui.menuview.MenuOption
import com.osprey.common.ui.menuview.MenuOptionListener
import com.osprey.common.ui.menuview.MenuOptionType
import com.osprey.common.ui.menuview.MenuView
import com.osprey.core.base.activity.BaseActivity
import com.osprey.core.extension.debounceClick
import com.osprey.main.R
import com.osprey.main.databinding.ActivityMainBinding
import com.osprey.main.ui.main.MainUiAction.ChangeTab
import com.osprey.main.ui.main.bottombar.BottomBarMenu
import com.osprey.main.ui.main.bottombar.BottomBarView
import com.wodox.common.navigation.IntroNavigator
import com.osprey.main.model.Constants
import com.osprey.main.util.AddDeviceUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class) {
    override fun layoutId(): Int = R.layout.activity_main

    @Inject
    lateinit var introNavigator: IntroNavigator

    @Inject
    lateinit var mainNavigator: MainNavigator

    private val pagerBottomAdapter by lazy(LazyThreadSafetyMode.NONE) {
        MainBottomPagerAdapter(this)
    }

    private var isFirstLaunch = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            isFirstLaunch = intent.getBooleanExtra(Constants.Intents.IS_FIRST_LAUNCH, false)
        }
    }

    override fun initialize() {
        //    register()
        setupViewPagers()
        setupViewPagers()
        setupBottomBar()
        observeViewModel()
        observer()
        setupUI()
        setupAction()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }

    override fun onDestroy() {
        //   unregister()
        super.onDestroy()
    }

    private fun setupAction() {
        binding.apply {
            toolbarHeader.ivAdd.debounceClick {
                val menus = AddDeviceUtil.getItemMenus(this@MainActivity)
                MenuView.show(this@MainActivity, it, menus, object : MenuOptionListener {
                    override fun onClick(menu: MenuOption) {
                        when(menu.type){
                            MenuOptionType.ADD_DEVICE -> TODO()
                            MenuOptionType.CREATE_SCENE -> TODO()
                            MenuOptionType.SCAN -> TODO()
                             else -> {}
                        }
                    }
                })
            }
        }
    }

    private fun setupUI() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val user = FirebaseAuth.getInstance().currentUser
        val name = user?.displayName ?: "User"
    }

    private fun observer() {

    }

    private fun setupViewPagers() {
        binding.viewPagerBottom.run {
            adapter = pagerBottomAdapter
            offscreenPageLimit = 3
            isUserInputEnabled = false
        }
    }

    private fun setupBottomBar() {
        binding.bottomBarView.listener = object : BottomBarView.OnBottomBarViewListener {
            override fun onClick(menu: BottomBarMenu) {
                when (menu.type) {
                    BottomBarMenu.BottomBarMenuType.HOME -> {
                        binding.viewPagerBottom.setCurrentItem(0, false)
                        viewModel.dispatch(ChangeTab(BottomBarMenu.BottomBarMenuType.HOME))
                    }

                    BottomBarMenu.BottomBarMenuType.SCENE -> {
                        binding.viewPagerBottom.setCurrentItem(1, false)
                        viewModel.dispatch(ChangeTab(BottomBarMenu.BottomBarMenuType.SCENE))
                    }

                    BottomBarMenu.BottomBarMenuType.MALL -> {
                        binding.viewPagerBottom.setCurrentItem(2, false)
                        viewModel.dispatch(ChangeTab(BottomBarMenu.BottomBarMenuType.MALL))
                    }

                    BottomBarMenu.BottomBarMenuType.PROFILE -> {
                        binding.viewPagerBottom.setCurrentItem(3, false)
                        viewModel.dispatch(ChangeTab(BottomBarMenu.BottomBarMenuType.PROFILE))
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

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onLanguage(event: LanguageEvent) {
//        val codes = event.language.split("-")
//        val language = codes.getOrNull(0) ?: return
//        val country = codes.getOrNull(1)
//        applicationContext.changeLanguage(language, country)
//        baseContext.changeLanguage(language, country)
//        changeLanguage(language, country)
//        viewModel.context?.get()?.changeLanguage(language, country)
//        runDelayed(20) {
//            introNavigator.openSplash(this)
//            finish()
//        }
//    }
}