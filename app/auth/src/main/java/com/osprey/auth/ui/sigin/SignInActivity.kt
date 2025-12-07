package com.osprey.auth.ui.sigin

import com.osprey.common.navigation.MainNavigator
import com.osprey.core.base.activity.BaseActivity
import com.osprey.core.extension.debounceClick
import com.osprey.core.extension.launchWhenStarted
import com.osprey.core.extension.toast
import com.wodox.auth.R
import com.wodox.auth.databinding.ActivtySignInOutLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivtySignInOutLayoutBinding, SignUpViewModel>(
    SignUpViewModel::class) {
    @Inject
    lateinit var mainNavigator: MainNavigator
    override fun layoutId(): Int = R.layout.activty_sign_in_out_layout

    override fun initialize() {
        setupUI()
        setupAction()
        observer()
    }

//    private fun setupUI() {
//        if (viewModel.isShowSignUp) {
//            binding.btnSignIn.text = getString(com.osprey.resources.R.string.sign_up)
//            binding.tvSignInTitle.text = getString(com.osprey.resources.R.string.sign_up)
//        } else {
//            binding.etFullName.gone()
//            binding.btnSignIn.text = getString(com.osprey.resources.R.string.sign_in)
//        }
    //}

    private fun setupUI() {

    }

    private fun setupAction() {
        binding.ivBack.debounceClick {
            finish()
        }
//        binding.btnSignIn.isEnabled = false
//        binding.btnSignIn.alpha = 0.5f
//
//        val watcher = object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//            }
//
//            override fun beforeTextChanged(
//                s: CharSequence?,
//                start: Int,
//                count: Int,
//                after: Int
//            ) {
//            }
//            override fun onTextChanged(
//                s: CharSequence?,
//                start: Int,
//                before: Int,
//                count: Int
//            ) {
//                val username = binding.etEmail.text.toString().trim()
//                val password = binding.etPassword.text.toString().trim()
//                val isEnabled = username.isNotEmpty() && password.isNotEmpty()
//                binding.btnSignIn.isEnabled = isEnabled
//                binding.btnSignIn.alpha = if (isEnabled) 1f else 0.5f
//            }
//        }
//        binding.etEmail.addTextChangedListener(watcher)
//        binding.etPassword.addTextChangedListener(watcher)
//        binding.btnSignIn.debounceClick {
//            val username = binding.etEmail.text.toString().trim()
//            val password = binding.etPassword.text.toString().trim()
//            val fullName = binding.etFullName.text.toString().trim()
//
//            if (username.isEmpty() || password.isEmpty()) {
//                Toast.makeText(this@SignInActivity, "Please fill in all fields", Toast.LENGTH_SHORT)
//                    .show()
//                return@debounceClick
//            }
//
//            if (viewModel.isShowSignUp) {
//                viewModel.dispatch(SignUpUiAction.SignUp(username, password, fullName))
//            } else {
//                viewModel.dispatch(SignUpUiAction.SignIn(username, password))
//            }
//        }
        binding.clLanguage.debounceClick {

        }
        binding.etUsername.debounceClick {

        }
        binding.etPassword.debounceClick {

        }
        binding.cbAgreement.setOnCheckedChangeListener { _, _ ->
            updateLoginButtonState()
        }

        binding.btnLogin.debounceClick {
            if (!binding.cbAgreement.isChecked) {
                toast("Please agree to the policies")
                return@debounceClick
            }
            val email = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                toast("Please fill in all fields")
                return@debounceClick
            }
//            if (viewModel.isShowSignUp) {
//                viewModel.dispatch(SignUpUiAction.SignUp(username, password))
//            } else {
            viewModel.dispatch(SignUpUiAction.SignIn(email, password))
        }
    }


    private fun updateLoginButtonState() {
        val checked = binding.cbAgreement.isChecked
        binding.btnLogin.isEnabled = checked
        binding.btnLogin.backgroundTintList =
            getColorStateList(if (checked) android.R.color.holo_blue_dark else android.R.color.darker_gray)
    }


    private fun observer() {
        launchWhenStarted {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is SignUpUiEvent.Loading -> {
                        showLoading(event.loading)
                    }

                    is SignUpUiEvent.Success -> {
                        showLoading(false)
                        toast(event.message)
                        mainNavigator.showMain(this@SignInActivity, true)
                    }

                    is SignUpUiEvent.Error -> {
                        showLoading(false)
                        toast("Inccorect username or password.Please try again")
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
//        binding.loadingOverlay.visibility =
//            if (isLoading) View.VISIBLE else View.GONE
//        binding.llInput.visibility =
//            if (!isLoading) View.VISIBLE else View.GONE
    }

}
