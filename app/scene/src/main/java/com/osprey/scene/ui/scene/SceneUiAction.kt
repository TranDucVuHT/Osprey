package com.osprey.scene.ui.scene

import com.osprey.scene.ui.menu.SceneBarMenu

sealed class SceneUiAction {
    data class ChangeTab(val type: SceneBarMenu.TopBarMenuType) : SceneUiAction()
}