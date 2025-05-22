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

public val MyIconPack.Volumeoff: ImageVector
    get() {
        if (_volumeoff != null) {
            return _volumeoff!!
        }
        _volumeoff = Builder(name = "Volumeoff", defaultWidth = 200.0.dp, defaultHeight = 200.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveToRelative(7.532f, 17.0f)
                lineToRelative(5.0f, 6.0f)
                lineTo(15.0f, 23.0f)
                lineTo(15.0f, 1.0f)
                horizontalLineToRelative(-2.468f)
                lineToRelative(-5.0f, 6.0f)
                lineTo(1.0f, 7.0f)
                verticalLineToRelative(10.0f)
                close()
                moveTo(3.0f, 9.0f)
                horizontalLineToRelative(5.468f)
                lineTo(13.0f, 3.562f)
                verticalLineToRelative(16.876f)
                lineTo(8.468f, 15.0f)
                lineTo(3.0f, 15.0f)
                close()
                moveTo(21.9f, 12.0f)
                lineTo(24.0f, 9.9f)
                lineToRelative(-1.4f, -1.4f)
                lineToRelative(-2.1f, 2.1f)
                lineToRelative(-2.1f, -2.1f)
                lineTo(17.0f, 9.9f)
                lineToRelative(2.1f, 2.1f)
                lineToRelative(-2.1f, 2.1f)
                lineToRelative(1.4f, 1.4f)
                lineToRelative(2.1f, -2.1f)
                lineToRelative(2.1f, 2.1f)
                lineToRelative(1.4f, -1.4f)
                close()
            }
        }
        .build()
        return _volumeoff!!
    }

private var _volumeoff: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Volumeoff, contentDescription = "")
    }
}
