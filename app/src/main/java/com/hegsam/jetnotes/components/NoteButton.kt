package com.hegsam.jetnotes.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(onClick = {onClick.invoke()}, shape = CircleShape, enabled = enabled, modifier = modifier) {
        Text(text = text)
    }

}