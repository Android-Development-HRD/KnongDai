package com.example.vechet.knongdai.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void addUser(User user);
    @Update
    void updateUser(User user);
    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM users")
    List<User> getAllUser();

    @Query("DELETE FROM users WHERE name = :name")
    void removeByName(String name);
}