package com.example.appcare.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appcare.Pojos.ProfilePojo;

import java.util.List;

@Dao
public interface ProfileDao {

    @Query("SELECT *FROM ProfilePojo")
    List<ProfilePojo> getAllProfile();

    @Insert
    void insert(ProfilePojo profilePojo);

    @Delete
    void delete(ProfilePojo profilePojo);

    @Update
    void update(ProfilePojo profilePojo);



}
