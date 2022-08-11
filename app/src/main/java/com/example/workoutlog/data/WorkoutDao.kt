package com.example.workoutlog.data

import android.content.ClipData
import androidx.room.*


@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Workout)

    @Update
    suspend fun update(item: Workout)

    @Delete
    suspend fun delete(item: Workout)
}