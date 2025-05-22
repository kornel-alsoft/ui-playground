package com.kjursa.android.hikornel.ui.theme.icons.myiconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kjursa.android.hikornel.ui.theme.icons.MyIconPack
import kotlin.Unit

public val MyIconPack.Sun: ImageVector
    get() {
        if (_sun != null) {
            return _sun!!
        }
        _sun = Builder(name = "Sun", defaultWidth = 200.0.dp, defaultHeight = 200.0.dp,
                viewportWidth = 48.0f, viewportHeight = 48.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 4.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveToRelative(9.15f, 9.15f)
                lineToRelative(2.228f, 2.228f)
                moveTo(3.0f, 24.0f)
                horizontalLineToRelative(3.15f)
                moveToRelative(3.0f, 14.85f)
                lineToRelative(2.228f, -2.228f)
                moveTo(38.85f, 38.85f)
                lineToRelative(-2.228f, -2.228f)
                moveTo(45.0f, 24.0f)
                horizontalLineToRelative(-3.15f)
                moveToRelative(-3.0f, -14.85f)
                lineToRelative(-2.228f, 2.228f)
                moveTo(24.0f, 3.0f)
                verticalLineToRelative(3.15f)
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 4.0f, strokeLineCap = Butt, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(24.0f, 36.0f)
                curveToRelative(6.627f, 0.0f, 12.0f, -5.373f, 12.0f, -12.0f)
                reflectiveCurveToRelative(-5.373f, -12.0f, -12.0f, -12.0f)
                reflectiveCurveToRelative(-12.0f, 5.373f, -12.0f, 12.0f)
                reflectiveCurveToRelative(5.373f, 12.0f, 12.0f, 12.0f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 4.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(24.0f, 45.0f)
                verticalLineToRelative(-3.15f)
            }
        }
        .build()
        return _sun!!
    }

private var _sun: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Sun, contentDescription = "")
    }
}
