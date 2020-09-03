package com.example.structure.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blackice.business.data.local_db.entity.Category
import com.example.structure.data.local.dao.CategoryDao


@Database(entities = arrayOf(Category::class) , version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
}