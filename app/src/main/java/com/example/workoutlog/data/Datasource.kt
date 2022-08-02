package com.example.workoutlog.data
import com.example.workoutlog.model.workout
import com.example.workoutlog.R


class Datasource {

    fun loadWorkouts(): List<workout> {
        //maybeeeee iterate over the database of workout???
        return listOf<workout>(
            workout(R.string.workout1)

        )
    }
}