package com.example.workoutlog.data

import android.content.ClipData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(workout: Workout)

    @Update
    suspend fun update(workout: Workout)

    @Delete
    suspend fun delete(workout: Workout)

    @Query("SELECT * from workout WHERE id = :id")
    fun getWorkout(id: Int): Flow<Workout>

    @Query("SELECT * from workout ORDER BY name ASC")
    fun getWorkouts(): Flow<List<Workout>>
}