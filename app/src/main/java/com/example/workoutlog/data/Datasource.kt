package com.example.workoutlog.data
import com.example.workoutlog.model.workout
import com.example.workoutlog.R


class Datasource {

    fun loadWorkouts(): List<workout> {
        return listOf<workout>(
            workout(R.string.affirmation1),

        )
    }
}