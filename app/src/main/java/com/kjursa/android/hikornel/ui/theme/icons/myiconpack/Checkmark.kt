package com.kjursa.android.hikornel.ui.theme.icons.myiconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kjursa.android.hikornel.ui.theme.icons.MyIconPack
import kotlin.Unit

public val MyIconPack.Checkmark: ImageVector
    get() {
        if (_checkmark != null) {
            return _checkmark!!
        }
        _checkmark = Builder(name = "Checkmark", defaultWidth = 200.0.dp, defaultHeight = 200.0.dp,
                viewportWidth = 1024.0f, viewportHeight = 830.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(1006.0f, 195.0f)
                lineTo(387.0f, 813.0f)
                quadToRelative(-18.0f, 18.0f, -53.0f, 17.0f)
                quadToRelative(-32.0f, -1.0f, -49.0f, -17.0f)
                lineTo(18.0f, 546.0f)
                quadTo(0.0f, 528.0f, 0.0f, 502.0f)
                reflectiveQuadToRelative(18.0f, -44.0f)
                lineToRelative(89.0f, -89.0f)
                quadToRelative(18.0f, -18.0f, 44.0f, -18.0f)
                reflectiveQuadToRelative(44.0f, 18.0f)
                lineToRelative(141.0f, 141.0f)
                lineTo(829.0f, 18.0f)
                quadToRelative(18.0f, -18.0f, 44.0f, -18.0f)
                reflectiveQuadToRelative(44.0f, 18.0f)
                lineToRelative(89.0f, 88.0f)
                quadToRelative(18.0f, 19.0f, 18.0f, 44.5f)
                reflectiveQuadToRelative(-18.0f, 44.5f)
                close()
            }
        }
        .build()
        return _checkmark!!
    }

private var _checkmark: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Checkmark, contentDescription = "")
    }
}
