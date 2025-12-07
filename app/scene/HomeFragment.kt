@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeLayoutBinding, HomeViewModel>(
    HomeViewModel::class
) {
    @Inject
    lateinit var homeNavigator: HomeNavigator


    override fun layoutId(): Int = R.layout.fragment_home_layout

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
        fun newInstance() = HomeFragment()
    }
}