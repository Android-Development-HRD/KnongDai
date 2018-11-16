package com.example.vechet.knongdai.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import io.reactivex.annotations.NonNull;

@Entity(tableName = "users")
public class User {
    @PrimaryKey
    public int id;
    public String name;
    public String email;
    @ColumnInfo(name = "profile_url")
    public String profileUrl;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                '}';
    }
}