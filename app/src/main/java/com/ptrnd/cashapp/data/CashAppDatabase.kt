package com.ptrnd.cashapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ptrnd.cashapp.viewmodel.UserViewModel

@Database(entities = [Flow::class, User::class], version = 1, exportSchema = false)
abstract class CashAppDatabase: RoomDatabase() {
    abstract fun flowDao(): FlowDao
    abstract fun userDao(): UserDao

    private lateinit var mUserViewModel: UserViewModel

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
                )
                    .addCallback(callback)
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        private val callback = object : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                db.execSQL("INSERT INTO user (id_user, username, password) VALUES (0, 'user', 'user')")
            }
        }
    }
}