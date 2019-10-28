package com.izinstudio.loginmvp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM User")
    List<User> getUser();

    @Insert()
    void insertUser(User user);
}
