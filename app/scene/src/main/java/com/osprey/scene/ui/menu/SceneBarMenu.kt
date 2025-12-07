package com.osprey.scene.ui.menu


import android.content.Context
import com.osprey.core.data.model.Selectable
import com.osprey.resources.R

data class SceneBarMenu(
    val type: TopBarMenuType,
    val title: String,
    val icon: Int,
    val selectedIcon: Int,
    override var isSelected: Boolean = false
) : Selectable {
    enum class TopBarMenuType(val route: String) {
        AUTOMATION("automation"),
        TAPTORUN("taptorun");
    }

    companion object {
        fun getDefaults(context: Context): List<SceneBarMenu> {
            return listOf(
                SceneBarMenu(
                    type = TopBarMenuType.AUTOMATION,
                    title = context.getString(R.string.automation),
                    icon = R.drawable.ic_bottom_bar_home,
                    selectedIcon = R.drawable.ic_bottom_bar_home_selected
                ),
                SceneBarMenu(
                    type = TopBarMenuType.TAPTORUN,
                    title = context.getString(R.string.tap_to_run),
                    icon = R.drawable.ic_bottom_bar_lesson,
                    selectedIcon = R.drawable.ic_bottom_bar_lesson_selected
                ),
            )
        }
    }
}