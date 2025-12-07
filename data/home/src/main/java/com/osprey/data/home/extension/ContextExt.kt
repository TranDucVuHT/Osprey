package com.osprey.data.home.extension

import android.content.Context
import com.osprey.core.app.AbstractApplication
import com.osprey.data.common.datasource.AppSharePrefs


val Context.appSharePrefs
    get() = (applicationContext as AbstractApplication).sharePrefs as AppSharePrefs

val Context.isPremium
    get() = (applicationContext as AbstractApplication).isPremium
