package com.osprey.data.common.extension

import android.content.Context
import com.osprey.core.app.AbstractApplication

val Context.isPremium
    get() = (applicationContext as AbstractApplication).isPremium
