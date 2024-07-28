package com.example.myapplication.room.db

import android.content.Context
import android.media.AudioRecord
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import kotlin.concurrent.Volatile

@Database(
    entities = [Subscriber::class],
    version = 5,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4, spec = SubscriberDatabase.Migration3To4::class),
        AutoMigration(from = 4, to = 5, spec = SubscriberDatabase.Migration4To5::class)] )

abstract class SubscriberDatabase: RoomDatabase() {

   abstract val subscriberDAO: SubscriberDAO

   @RenameColumn(tableName = "subscriber_db", fromColumnName = "subscriber_name", toColumnName = "subscriber_firstname")
   class Migration3To4 : AutoMigrationSpec

    @DeleteColumn(tableName = "subscriber_db", columnName = "subscriber_email" )
    class Migration4To5 : AutoMigrationSpec

   companion object{
       @Volatile
       private var INSTANCE : SubscriberDatabase? = null

       fun getInstance(context: Context): SubscriberDatabase {
           synchronized(this){
               var instance = INSTANCE
               if(instance == null){
                   instance = Room.databaseBuilder(
                       context.applicationContext,
                       SubscriberDatabase::class.java,
                       "subscriber_db"
                   ).build()
                   INSTANCE = instance
               }
               return instance
           }
       }
   }
}