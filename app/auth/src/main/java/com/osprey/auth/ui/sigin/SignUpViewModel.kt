package com.osprey.auth.ui.sigin

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.osprey.core.viewmodel.BaseUiStateViewModel
import com.osprey.domain.user.model.request.LoginRequest
import com.osprey.domain.user.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val app: Application,
    private val loginUseCase: LoginUseCase,
) : BaseUiStateViewModel<SignUpUiState, SignUpUiEvent, SignUpUiAction>(app) {
    override fun initialState(): SignUpUiState = SignUpUiState()
    override fun handleAction(action: SignUpUiAction) {
        when (action) {
            is SignUpUiAction.SignUp -> signUp(action.email, action.password, action.fullName)
            is SignUpUiAction.SignIn -> signIn(action.email, action.password)
        }
    }

    private fun signUp(email: String, password: String, fullName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                sendEvent(SignUpUiEvent.Loading(true))
            }

        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            sendEvent(SignUpUiEvent.Loading(true))

            try {
                val user = loginUseCase(
                    LoginRequest(
                        username = email,
                        password = password
                    )
                )
                withContext(Dispatchers.Main) {
                    if (user != null) {
                        sendEvent(SignUpUiEvent.Success("Đăng nhập thành công"))
                    } else {
                        sendEvent(SignUpUiEvent.Error("Sai username hoặc mật khẩu"))
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    sendEvent(SignUpUiEvent.Error(e.message ?: "Lỗi không xác định"))
                }
            } finally {
                withContext(Dispatchers.Main) {
                    sendEvent(SignUpUiEvent.Loading(false))
                }
            }
        }
    }

}
