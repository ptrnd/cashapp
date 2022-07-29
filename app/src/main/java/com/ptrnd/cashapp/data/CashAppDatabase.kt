package com.ptrnd.cashapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Flow::class, User::class], version = 1, exportSchema = false)
abstract class CashAppDatabase: RoomDatabase() {
    abstract fun flowDao(): FlowDao
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: CashAppDatabase? = null

        fun getDatabase(context: Context): CashAppDatabase{
            val tempInstance = INSTANCE
            //jika instance sudah dibuat, tidak perlu membuat lagi
            if(tempInstance != null){
                return tempInstance
            }

            //jika kosong, sistem membuat database baru
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CashAppDatabase::class.java,
                    "cashapp_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}