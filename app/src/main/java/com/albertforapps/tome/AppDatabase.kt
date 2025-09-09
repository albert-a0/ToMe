package com.albertforapps.tome

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoData::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract  fun todoDao(): TodoDataDao
}