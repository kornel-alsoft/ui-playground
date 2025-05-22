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

public val MyIconPack.Volumeon: ImageVector
    get() {
        if (_volumeon != null) {
            return _volumeon!!
        }
        _volumeon = Builder(name = "Volumeon", defaultWidth = 200.0.dp, defaultHeight = 200.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveToRelative(12.532f, 23.0f)
                lineToRelative(-5.0f, -6.0f)
                lineTo(1.0f, 17.0f)
                lineTo(1.0f, 7.0f)
                horizontalLineToRelative(6.532f)
                lineToRelative(5.0f, -6.0f)
                lineTo(15.0f, 1.0f)
                verticalLineToRelative(22.0f)
                close()
                moveTo(8.468f, 9.0f)
                lineTo(3.0f, 9.0f)
                verticalLineToRelative(6.0f)
                horizontalLineToRelative(5.468f)
                lineTo(13.0f, 20.438f)
                lineTo(13.0f, 3.562f)
                close()
                moveTo(19.0f, 17.0f)
                lineTo(19.0f, 7.0f)
                horizontalLineToRelative(-2.0f)
                verticalLineToRelative(10.0f)
                close()
                moveTo(23.0f, 4.0f)
                verticalLineToRelative(16.0f)
                horizontalLineToRelative(-2.0f)
                lineTo(21.0f, 4.0f)
                close()
            }
        }
        .build()
        return _volumeon!!
    }

private var _volumeon: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Volumeon, contentDescription = "")
    }
}
