package com.osprey.scene.ui.scene

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.osprey.core.extension.toArrayList
import com.osprey.core.viewmodel.BaseUiStateViewModel
import com.osprey.scene.ui.menu.SceneBarMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SceneViewModel @Inject constructor(
    val app: Application,
) : BaseUiStateViewModel<SceneUiState, SceneUiEvent,SceneUiAction>(app) {
    override fun initialState(): SceneUiState = SceneUiState()
    val topBarMenus: List<SceneBarMenu> = SceneBarMenu.getDefaults(applicationContext())

    val changePageBottomEvent = MutableLiveData<Int>()


    override fun handleAction(action: SceneUiAction) {
        when (action) {
            is SceneUiAction.ChangeTab -> changeTab(action.type)
        }
    }

    private fun changeTab(type: SceneBarMenu.TopBarMenuType) {
        val menus = uiState.value.menus.toArrayList().onEach {
            it.isSelected = it.type == type
        }
        updateState { it.copy(menus = menus,
        )
        }
        changePageBottomEvent.value = when(type){
            SceneBarMenu.TopBarMenuType.AUTOMATION -> 1
            SceneBarMenu.TopBarMenuType.TAPTORUN -> 2
        }
    }
}