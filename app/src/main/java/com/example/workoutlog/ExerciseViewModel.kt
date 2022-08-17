package com.example.workoutlog

import android.content.ClipData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.workoutlog.data.Exercise
import com.example.workoutlog.data.ExerciseDao
import kotlinx.coroutines.launch

class ExerciseViewModel(private val exerciseDao: ExerciseDao) : ViewModel() {

    private fun insertExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseDao.insert(exercise)
        }
    }

    private fun getNewExerciseEntry(exerciseName: String, workoutId: Int,
                                    set1weight: Int, set2weight: Int, set3weight: Int, set4weight: Int, set5weight: Int, set6weight: Int, set7weight: Int,
                                    set1reps: Int,  set2reps: Int,  set3reps: Int,  set4reps: Int,  set5reps: Int,  set6reps: Int,  set7reps: Int,): Exercise {
        return Exercise(
            exerciseName = exerciseName,
            workoutId = workoutId,
            set1weight = set1weight,
            set2weight = set2weight,
            set3weight = set3weight,
            set4weight = set4weight,
            set5weight = set5weight,
            set6weight = set6weight,
            set7weight = set7weight,
            set1reps = set1reps,
            set2reps = set2reps,
            set3reps = set3reps,
            set4reps = set4reps,
            set5reps = set5reps,
            set6reps = set6reps,
            set7reps = set7reps
        )
    }

    fun addNewExercise(exerciseName: String, workoutId: Int,
                                    set1weight: Int, set2weight: Int, set3weight: Int, set4weight: Int, set5weight: Int, set6weight: Int, set7weight: Int,
                                    set1reps: Int,  set2reps: Int,  set3reps: Int,  set4reps: Int,  set5reps: Int,  set6reps: Int,  set7reps: Int,) {
        val newExercise = getNewExerciseEntry(exerciseName, workoutId,
            set1weight, set2weight, set3weight, set4weight, set5weight, set6weight, set7weight,
            set1reps,  set2reps,  set3reps,  set4reps,  set5reps,  set6reps,  set7reps)
        insertExercise(newExercise)
    }

    fun isEntryValid(ExerciseName: String): Boolean {
        if (ExerciseName.isBlank()) {
            return false
        }
        return true
    }

}


class ExerciseViewModelFactory(private val exerciseDao: ExerciseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExerciseViewModel(exerciseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}