package com.example.roomdatabase.dao

import androidx.room.*
import com.example.roomdatabase.entity.Student

@Dao
interface StudentDAO {
    @Insert
    suspend fun insertStudent(student : Student)

    @Query("SELECT * FROM Student")
    suspend fun getAllStudents() : List<Student>

    @Update
    suspend fun updateStudent(student : Student)

    @Delete
    suspend fun DeleteStudent(student : Student)
}