package com.example.workoutlog

import android.app.Application
import com.example.workoutlog.data.Exercise
import com.example.workoutlog.data.ExerciseRoomDatabase
import com.example.workoutlog.data.WorkoutRoomDatabase

class WorkoutApplication : Application(){
    val database: WorkoutRoomDatabase by lazy { WorkoutRoomDatabase.getDatabase(this) }
    val edatabase: ExerciseRoomDatabase by lazy { ExerciseRoomDatabase.getDatabase(this) }
}