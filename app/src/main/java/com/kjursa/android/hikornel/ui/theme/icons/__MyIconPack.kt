package com.kjursa.android.hikornel.ui.theme.icons

import androidx.compose.ui.graphics.vector.ImageVector
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Checkmark
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Cros
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Moon
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Sun
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Volumeoff
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Volumeon
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Checkmark, Cros, Moon, Sun, Volumeoff, Volumeon)
    return __AllIcons!!
  }
