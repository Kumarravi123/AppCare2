package com.example.appcare.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.appcare.Pojos.ProfilePojo;

@Database(entities = {ProfilePojo.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProfileDao profileDao();
   private static AppDatabase INSTANCE;
   public   static AppDatabase getAppDatabase(Context context){
       if(INSTANCE==null)
       {
           INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"Profile Database")
                   .allowMainThreadQueries()
                   .build();
       }
       return  INSTANCE;
   }

   public static void destoryInstance()
   {
       INSTANCE=null;
   }



}
