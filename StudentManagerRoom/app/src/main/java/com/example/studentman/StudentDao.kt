package com.example.studentman

import androidx.room.*

@Dao
interface StudentDao {
    @Insert
    suspend fun insert(student: Student)

    @Delete
    suspend fun delete(student: Student)

    @Query("SELECT * FROM students")
    suspend fun getAllStudents(): List<Student>
}