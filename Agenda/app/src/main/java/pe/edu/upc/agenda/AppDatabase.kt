package pe.edu.upc.agenda

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Contact::class), version = 1, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): ContactDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "app"
                ).allowMainThreadQueries().build()
            }

            return INSTANCE as AppDatabase
        }
    }
}