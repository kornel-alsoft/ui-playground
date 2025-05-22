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

public val MyIconPack.Cros: ImageVector
    get() {
        if (_cros != null) {
            return _cros!!
        }
        _cros = Builder(name = "Cros", defaultWidth = 200.0.dp, defaultHeight = 200.0.dp,
                viewportWidth = 64.0f, viewportHeight = 64.0f).apply {
            path(fill = SolidColor(Color(0xFFec1c24)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(50.592f, 2.291f)
                lineTo(32.0f, 20.884f)
                curveTo(25.803f, 14.689f, 19.604f, 8.488f, 13.406f, 2.291f)
                curveToRelative(-7.17f, -7.17f, -18.284f, 3.948f, -11.12f, 11.12f)
                curveToRelative(6.199f, 6.193f, 12.4f, 12.395f, 18.592f, 18.592f)
                arcTo(32589.37f, 32589.37f, 0.0f, false, true, 2.286f, 50.595f)
                curveToRelative(-7.164f, 7.168f, 3.951f, 18.283f, 11.12f, 11.12f)
                curveToRelative(6.197f, -6.199f, 12.396f, -12.399f, 18.593f, -18.594f)
                lineToRelative(18.592f, 18.594f)
                curveToRelative(7.17f, 7.168f, 18.287f, -3.951f, 11.12f, -11.12f)
                curveToRelative(-6.199f, -6.199f, -12.396f, -12.396f, -18.597f, -18.594f)
                curveToRelative(6.2f, -6.199f, 12.397f, -12.398f, 18.597f, -18.596f)
                curveToRelative(7.168f, -7.166f, -3.949f, -18.284f, -11.12f, -11.11f)
            }
        }
        .build()
        return _cros!!
    }

private var _cros: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Cros, contentDescription = "")
    }
}
