package com.osprey.scene.ui.scene

import com.osprey.scene.ui.menu.SceneBarMenu

data class SceneUiState (
    val menus: List<SceneBarMenu> = arrayListOf(),
    )