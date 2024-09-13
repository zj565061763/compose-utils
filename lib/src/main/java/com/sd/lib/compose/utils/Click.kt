package com.sd.lib.compose.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role

/**
 * 点击事件，不带涟漪效果
 */
fun Modifier.fClick(
   interactionSource: MutableInteractionSource? = null,
   indication: Indication? = null,
   enabled: Boolean = true,
   onClickLabel: String? = null,
   role: Role? = null,
   onClick: () -> Unit,
) = clickable(
   interactionSource = interactionSource,
   indication = indication,
   enabled = enabled,
   onClickLabel = onClickLabel,
   role = role,
   onClick = onClick,
)

/**
 * 点击事件，不带涟漪效果
 */
@OptIn(ExperimentalFoundationApi::class)
fun Modifier.fCombinedClick(
   interactionSource: MutableInteractionSource? = null,
   indication: Indication? = null,
   enabled: Boolean = true,
   onClickLabel: String? = null,
   role: Role? = null,
   onLongClickLabel: String? = null,
   onLongClick: (() -> Unit)? = null,
   onDoubleClick: (() -> Unit)? = null,
   onClick: () -> Unit,
) = combinedClickable(
   interactionSource = interactionSource,
   indication = indication,
   enabled = enabled,
   onClickLabel = onClickLabel,
   role = role,
   onLongClickLabel = onLongClickLabel,
   onLongClick = onLongClick,
   onDoubleClick = onDoubleClick,
   onClick = onClick,
)