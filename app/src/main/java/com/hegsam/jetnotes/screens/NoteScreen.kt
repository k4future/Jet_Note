package com.hegsam.jetnotes.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hegsam.jetnotes.R
import com.hegsam.jetnotes.components.NoteButton
import com.hegsam.jetnotes.components.NoteInputText
import com.hegsam.jetnotes.components.NoteRow
import com.hegsam.jetnotes.models.Note

@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {

    val context = LocalContext.current

    val title = remember {
        mutableStateOf("")
    }

    val description = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(6.dp)) {

        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon")
        })

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = title.value,
                label = "Title",
                singleLine = true,
                onTextChange = {
                    if (it.all { char ->
                            char.isLetterOrDigit() || char.isWhitespace()

                        }) title.value = it
                })

            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = description.value,
                label = "Add a note",
                maxLines = 100,
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()

                        }) description.value = it
                })

            NoteButton(text = "Save", onClick = {
                if (title.value.isNotEmpty() && description.value.isNotEmpty()) {
                    onAddNote(Note(title = title.value, description = description.value))
                    title.value = ""
                    description.value = ""
                    Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                }
            })

            Divider(modifier = Modifier.padding(10.dp))

            LazyColumn {
                items(notes) { note ->
                    NoteRow(note = note, onNoteClicked = {
                        title.value = note.title
                        description.value = note.description
                    }, onNoteLongPressed = {
                        onRemoveNote(it)
                    })
                }
            }

        }
    }
}