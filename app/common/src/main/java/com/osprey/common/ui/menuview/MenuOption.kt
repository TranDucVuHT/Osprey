package com.osprey.common.ui.menuview

import kotlinx.serialization.Serializable

enum class MenuOptionType() {
    ADD_DEVICE, CREATE_SCENE, SCAN, SHARE, HISTORY, TURN_ON_SURVEY, PIN, DUPLICATE, DELETE, UNPIN, SELECT, ARCHIVE, UNARCHIVE, LOCK, SHARE_PDF, VOICE, CLEAR_CHAT, EDIT_CHARACTER, ADD_IMAGE, ADD_PDF,
    TODO,IN_PROGRESS,COMPLETE, RENAME, DOWNLOAD, BACKGROUND,EDIT_TITLE,REVERT,FAVOURITE,UN_FAVOURITE
}

@Serializable
data class MenuOption(
    val type: MenuOptionType,
    val iconResId: Int,
    val nameResId: Int,
    val toggleEnabled: Boolean = false,
    val isChecked: Boolean = false,
    val tintColor: Int? = null,
) {
}