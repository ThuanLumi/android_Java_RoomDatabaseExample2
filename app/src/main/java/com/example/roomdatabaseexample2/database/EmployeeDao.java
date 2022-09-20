package com.example.roomdatabaseexample2.database;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Insert(onConflict = REPLACE)
    void insertEmploy(Employee employee);

    @Insert(onConflict = IGNORE)
    void insertOrReplaceEmploy(Employee... employees);

    @Update(onConflict = REPLACE)
    void updateEmploy(Employee employee);

    @Query("DELETE FROM Employee")
    void deleteAll();

    @Query("SELECT * FROM Employee")
    public List<Employee> findAllEmploySync();
}
