package com.example.roomdatabaseexample2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Employee.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;


    public abstract EmployeeDao employeeDao();


    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            // To simplify the codelab, allow queries on the main thread.
            // Don't do this on a real app! See PersistenceBasicSample for an
            // example.
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                                    "AppDatabase").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
