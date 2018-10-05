package com.guskuma.notifique.data;

import android.arch.persistence.room.Room;
import android.content.Context;

public class AppDatabaseHelper {

    private static AppDatabase mDb = null;

    public static AppDatabase getDb(Context context){

        if(mDb == null) {
            mDb = Room.databaseBuilder(context,
                    AppDatabase.class, "notifique-database").build();
        }

        return mDb;
    }

}
