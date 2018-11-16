package com.example.vechet.knongdai.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(version = 1, entities = {User.class}, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public static final String DB_NAME = "User";

    public abstract UserDao getUserDao();

    public static UserDatabase getDatabase(Context context){
        return Room.databaseBuilder(context, UserDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
