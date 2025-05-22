package com.kjursa.android.hikornel.ui.theme.icons.myiconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
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

public val MyIconPack.Moon: ImageVector
    get() {
        if (_moon != null) {
            return _moon!!
        }
        _moon = Builder(name = "Moon", defaultWidth = 200.0.dp, defaultHeight = 200.0.dp,
                viewportWidth = 20.0f, viewportHeight = 20.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(7.455f, 2.004f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, true, 0.26f, 0.77f)
                arcToRelative(7.0f, 7.0f, 0.0f, false, false, 9.958f, 7.967f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, true, 1.067f, 0.853f)
                arcTo(8.5f, 8.5f, 0.0f, true, true, 6.647f, 1.921f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, true, 0.808f, 0.083f)
                close()
            }
        }
        .build()
        return _moon!!
    }

private var _moon: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Moon, contentDescription = "")
    }
}
