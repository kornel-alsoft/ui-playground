package com.kjursa.android.hikornel.app.presentation.widget

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AnimatedIconSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    imageOn: ImageVector,
    imageOff: ImageVector,
    style: AnimatedIconSwitchStyle = rememberAnimatedIconSwitchStyle()
) {
    var isPressed by remember { mutableStateOf(false) }

    val switchProgress by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = tween(durationMillis = 600)
    )
    val pivotProgress by animateFloatAsState(
        targetValue = if (isPressed) 1.5f else 1f,
        animationSpec = spring()
    )

    val currentChecked by rememberUpdatedState(checked)
    val currentOnCheckedChange by rememberUpdatedState(onCheckedChange)

    Box(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        try {
                            awaitRelease() // suspend until touch up or cancel
                            currentOnCheckedChange(!currentChecked)
                        } finally {
                            isPressed = false
                        }
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(64.dp)
                .height(32.dp)
                .background(
                    color = lerp(style.backgroundOffColor, style.backgroundOnColor, switchProgress),
                    shape = RoundedCornerShape(24.dp)
                )
        ) {
            AnimatedSwitchContent(imageOn, imageOff, style, switchProgress, pivotProgress)
        }
    }
}


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
private fun AnimatedSwitchContent(
    imageOn: ImageVector,
    imageOff: ImageVector,
    style: AnimatedIconSwitchStyle,
    progress: Float,
    pivotProgress: Float
) {
    BoxWithConstraints {
        val width = maxWidth
        val height = maxHeight

        val iconSize = 16.dp
        val padding = remember(height) { (height - iconSize) / 2 }
        val endOffset = width - height
        val currentOffset = endOffset * progress

        val pivotSize = maxHeight
        val endPivotOffset = width - pivotSize
        val currentPivotOffset = endPivotOffset * (1 - progress)

        Icon(
            imageVector = imageOn,
            contentDescription = null,
            tint = style.iconOnTint,
            modifier = Modifier
                .size(height)
                .padding(padding)
                .offset(x = currentOffset)
                .alpha(alpha = progress)
                .scale(scale = progress)
        )
        Icon(
            imageVector = imageOff,
            contentDescription = null,
            tint = style.iconOffTint,
            modifier = Modifier
                .size(height)
                .padding(padding)
                .offset(x = currentOffset)
                .alpha(alpha = 1f - progress)
                .scale(scale = 1f - progress)
        )

        Box(
            modifier = Modifier
                .size(pivotSize)
                .align(Alignment.CenterStart)
                .padding(all = 4.dp.times(pivotProgress))
                .offset(x = currentPivotOffset)
                .background(
                    color = lerp(style.thumbOffColor, style.thumbOnColor, progress),
                    shape = RoundedCornerShape(24.dp)
                )
        )
    }
}

@Composable
fun rememberAnimatedIconSwitchStyle(
    iconOnTint: Color = Color.Black,
    iconOffTint: Color = Color.DarkGray,
    thumbOnColor: Color = Color.White,
    thumbOffColor: Color = Color.White,
    backgroundOnColor: Color = MaterialTheme.colorScheme.primary,
    backgroundOffColor: Color = BackgroundOff,
): AnimatedIconSwitchStyle = remember(
    iconOnTint, iconOffTint, thumbOnColor, thumbOffColor, backgroundOnColor, backgroundOffColor
) {
    AnimatedIconSwitchStyle(
        iconOnTint = iconOnTint,
        iconOffTint = iconOffTint,
        thumbOnColor = thumbOnColor,
        thumbOffColor = thumbOffColor,
        backgroundOnColor = backgroundOnColor,
        backgroundOffColor = backgroundOffColor,
    )
}

data class AnimatedIconSwitchStyle(
    val iconOnTint: Color,
    val iconOffTint: Color,
    val thumbOnColor: Color,
    val thumbOffColor: Color,
    val backgroundOnColor: Color,
    val backgroundOffColor: Color,
)

val BackgroundOn = Color(0xFFFCB300)

val BackgroundOff = Color(0xFFdbdce0)
