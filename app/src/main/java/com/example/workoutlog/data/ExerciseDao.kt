package com.example.workoutlog.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercise: Exercise)

    @Update
    suspend fun update(exercise: Exercise)

    @Delete
    suspend fun delete(exercise: Exercise)

    @Query("SELECT * from exercise WHERE id = :id")
    fun getExercise(id: Int): Flow<Exercise>

    @Query("SELECT * from exercise WHERE workoutId = :id ORDER BY id DESC")
    fun getExercisesForWorkout(id: Int): Flow<List<Exercise>>
}