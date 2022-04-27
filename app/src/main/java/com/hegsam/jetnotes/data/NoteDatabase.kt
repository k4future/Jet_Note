package com.hegsam.jetnotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hegsam.jetnotes.models.Note
import com.hegsam.jetnotes.utils.DateConverter
import com.hegsam.jetnotes.utils.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class,UUIDConverter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDatabaseDao
}