package com.kjursa.android.hikornel.ui.theme.icons.myiconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kjursa.android.hikornel.ui.theme.icons.MyIconPack
import kotlin.Unit

public val MyIconPack.Settings: ImageVector
    get() {
        if (_settings != null) {
            return _settings!!
        }
        _settings = Builder(name = "Settings", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(12.0f, 15.0f)
                arcToRelative(3.0f, 3.0f, 0.0f, true, false, 0.0f, -6.0f)
                arcToRelative(3.0f, 3.0f, 0.0f, false, false, 0.0f, 6.0f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveToRelative(19.622f, 10.395f)
                lineToRelative(-1.097f, -2.65f)
                lineTo(20.0f, 6.0f)
                lineToRelative(-2.0f, -2.0f)
                lineToRelative(-1.735f, 1.483f)
                lineToRelative(-2.707f, -1.113f)
                lineTo(12.935f, 2.0f)
                horizontalLineToRelative(-1.954f)
                lineToRelative(-0.632f, 2.401f)
                lineToRelative(-2.645f, 1.115f)
                lineTo(6.0f, 4.0f)
                lineTo(4.0f, 6.0f)
                lineToRelative(1.453f, 1.789f)
                lineToRelative(-1.08f, 2.657f)
                lineTo(2.0f, 11.0f)
                verticalLineToRelative(2.0f)
                lineToRelative(2.401f, 0.655f)
                lineTo(5.516f, 16.3f)
                lineTo(4.0f, 18.0f)
                lineToRelative(2.0f, 2.0f)
                lineToRelative(1.791f, -1.46f)
                lineToRelative(2.606f, 1.072f)
                lineTo(11.0f, 22.0f)
                horizontalLineToRelative(2.0f)
                lineToRelative(0.604f, -2.387f)
                lineToRelative(2.651f, -1.098f)
                curveTo(16.697f, 18.831f, 18.0f, 20.0f, 18.0f, 20.0f)
                lineToRelative(2.0f, -2.0f)
                lineToRelative(-1.484f, -1.75f)
                lineToRelative(1.098f, -2.652f)
                lineToRelative(2.386f, -0.62f)
                verticalLineTo(11.0f)
                lineToRelative(-2.378f, -0.605f)
                close()
            }
        }
        .build()
        return _settings!!
    }

private var _settings: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Settings, contentDescription = "")
    }
}
