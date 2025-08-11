package com.emine.techopedia.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.emine.techopedia.model.Tech

@Database(entities = [Tech::class],version=1)
abstract class TechDatabase : RoomDatabase(){
    abstract fun techDAO(): TechDAO
    companion object {
        @Volatile
        private var instance: TechDatabase?=null
        private val lock= Any()
        operator fun invoke(context: Context)=instance?:synchronized(lock){
            instance?:databaseOlustur(context).also {
                instance=it
            }
        }
        private fun databaseOlustur(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            TechDatabase::class.java,
            "TechDatabase"
        ).build()
    }
}
/*
@Database(entities = [Tech::class],version=1)
abstract class TechDatabase: RoomDatabase(){
 abstract fun techDAO(): TechDAO
}*/
