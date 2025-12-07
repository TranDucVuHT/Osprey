package com.osprey.main.util


import android.content.Context
import com.osprey.common.ui.menuview.MenuOption
import com.osprey.common.ui.menuview.MenuOptionType
import com.osprey.resources.R

class AddDeviceUtil {
    companion object {
        fun getItemMenus(
            context: Context,
        ): ArrayList<MenuOption> {
            val menus = ArrayList<MenuOption>()

            menus.add(
                MenuOption(
                    type = MenuOptionType.PIN,
                    nameResId = R.string.add_device,
                    iconResId = R.drawable.ic_help,
                )
            )


            menus.add(
                MenuOption(
                    type = MenuOptionType.CREATE_SCENE,
                    nameResId = R.string.edit_name,
                    iconResId = R.drawable.ic_delete_draw,
                )
            )

            menus.add(
                MenuOption(
                    type = MenuOptionType.SCAN,
                    nameResId = com.osprey.core.R.string.scan,
                    iconResId = R.drawable.ic_delete_draw,
                    tintColor = context.getColor(R.color.colorDelete)
                )
            )
            return menus
        }
    }
}