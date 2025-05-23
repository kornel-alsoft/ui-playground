package com.kjursa.android.hikornel.ui.theme.icons

import androidx.compose.ui.graphics.vector.ImageVector
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Checkmark
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Cros
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Email
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Moon
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Sun
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Volumeoff
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Volumeon
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Home
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Message
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Text
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Checkmark, Cros, Email, Home, Message, Moon, Sun,
        Text, Volumeoff, Volumeon)
    return __AllIcons!!
  }
