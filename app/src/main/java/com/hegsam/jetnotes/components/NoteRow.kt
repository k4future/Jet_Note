package com.hegsam.jetnotes.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.hegsam.jetnotes.models.Note
import com.hegsam.jetnotes.utils.formatDate

@Composable
fun NoteRow(modifier: Modifier = Modifier, note: Note, onNoteClicked: (Note) -> Unit, onNoteLongPressed: (Note) -> Unit = {}) {

    Surface(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(), elevation = 6.dp
    ) {
        Column(
            modifier = modifier
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            onNoteClicked(note)
                        },
                        onLongPress = {
                            onNoteLongPressed(note)
                        }
                    )
                }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(text = formatDate(note.entryDate.time), style = MaterialTheme.typography.caption)

        }

    }

}