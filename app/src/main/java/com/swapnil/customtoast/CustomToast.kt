package com.swapnil.customtoast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CustomToast(
    message: String,
    borderColor: Color,
    imageVector: ImageVector,
    textColor: Color,
    progressBarColor: Color,
    backgroundColor: Color,
    durationMillis: Long = 3000, // Auto-dismiss time
    onDismiss: () -> Unit,
    visibility: Boolean,
    modifier: Modifier,
    alignment: Alignment,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    trackColor: Color = Color.LightGray,
    shadowCorner: Dp = 12.dp,
    shadowElevation: Dp = 6.dp,
    containerBorder: Dp = 8.dp,
    borderWidth: Dp = 2.dp,
    fontWeight: FontWeight = FontWeight.Bold,
    iconSize: Dp = 30.dp,
    closeIconSize: Dp = 25.dp,
    linerProgressIndicatorSize: Dp = 4.dp,
    columnModifier: Modifier = Modifier
        .shadow(shadowElevation, shape = RoundedCornerShape(shadowCorner))
        .background(backgroundColor, RoundedCornerShape(containerBorder))
        .border(borderWidth, borderColor, RoundedCornerShape(containerBorder))
        .fillMaxWidth(),
    boxModifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    progressBarModifier: Modifier = Modifier
        .fillMaxWidth()
        .height(linerProgressIndicatorSize),
    spacerWidth: Dp =8.dp
) {
    val progress = remember { Animatable(1f) }

    // Reset progress and start animation when visibility becomes true
    LaunchedEffect(visibility) {
        if (visibility) {
            progress.snapTo(1f) // Reset progress instantly
            progress.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis.toInt(), easing = LinearEasing)
            )
            onDismiss() // Dismiss toast after progress completion
        }
    }

    // Slide-Up Animation
    val animatedOffset by animateFloatAsState(
        targetValue = 0f,
        animationSpec = tween(800, easing = LinearOutSlowInEasing),
        label = "toast-slide-up"
    )

    Box(
        modifier = modifier
            .offset(y = animatedOffset.dp),
        contentAlignment = alignment
    ) {
        AnimatedVisibility(
            visible = visibility,
            enter = scaleIn(
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
            ),
            exit = scaleOut()
        ) {
            Column(
                modifier = columnModifier
            ) {
                Box(
                    modifier = boxModifier
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Icon(
                            imageVector = imageVector,
                            contentDescription = "Toast Icon",
                            tint = progressBarColor,
                            modifier = Modifier.size(iconSize)
                        )
                        Spacer(modifier = Modifier.width(spacerWidth))
                        Text(
                            text = message,
                            fontWeight = fontWeight,
                            color = textColor,
                            style = style
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Toast Icon",
                        tint = MaterialTheme.colorScheme.secondaryContainer,
                        modifier = Modifier
                            .size(closeIconSize)
                            .align(AbsoluteAlignment.CenterRight)
                            .clickable {
                                onDismiss()
                            }
                    )
                }

                // Smooth Progress Bar
                LinearProgressIndicator(
                    progress = progress.value, // Smooth animated progress
                    color = progressBarColor,
                    trackColor = trackColor,
                    modifier = progressBarModifier
                )
            }
        }
    }
}