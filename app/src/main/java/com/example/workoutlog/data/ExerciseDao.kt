package com.example.workoutlog.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking


@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercise: Exercise)

    @Update
    suspend fun update(exercise: Exercise)

    @Delete
    suspend fun delete(exercise: Exercise)

    @Query("DELETE from exercise WHERE workoutId = :id")
    suspend fun deleteExercisesById(id: Int)

    @Query("SELECT * from exercise WHERE id = :id")
    fun getExercise(id: Int): Flow<Exercise>

    @Query("SELECT * from exercise WHERE workoutId = :id ORDER BY id DESC")
    fun getExercisesForWorkout(id: Int): Flow<List<Exercise>>

    @Query("SELECT * from exercise group by exerciseName")
    fun getExercisesForProgress(): Flow<List<Exercise>>

    @Query("SELECT * from exercise WHERE exerciseName = :exerciseName ORDER BY id DESC")
    fun getExercisesByName(exerciseName: String): List<Exercise>

    @Query("SELECT MAX(MAX(set1weight), MAX(set2weight), MAX(set3weight), MAX(set4weight), MAX(set5weight), MAX(set6weight), MAX(set7weight)) from exercise WHERE exerciseName = :exerciseName")
    suspend fun getExerciseMaxWeight(exerciseName: String): Int
}