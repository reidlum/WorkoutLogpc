package com.example.workoutlog

import android.app.Application
import com.example.workoutlog.data.WorkoutRoomDatabase

class WorkoutApplication : Application(){
    val database: WorkoutRoomDatabase by lazy { WorkoutRoomDatabase.getDatabase(this) }
}