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
            workoutId = workoutId.toInt(),
            set1weight = set1weight.toString().toInt(),
            set2weight = set2weight.toString().toInt(),
            set3weight = set3weight.toString().toInt(),
            set4weight = set4weight.toString().toInt(),
            set5weight = set5weight.toString().toInt(),
            set6weight = set6weight.toString().toInt(),
            set7weight = set7weight.toString().toInt(),
            set1reps = set1reps.toString().toInt(),
            set2reps = set2reps.toString().toInt(),
            set3reps = set3reps.toString().toInt(),
            set4reps = set4reps.toString().toInt(),
            set5reps = set5reps.toString().toInt(),
            set6reps = set6reps.toString().toInt(),
            set7reps = set7reps.toString().toInt()
        )
    }

    fun addNewExercise(exerciseName: String, workoutId: Int,
                                    set1weight: String?, set2weight: String?, set3weight: String?, set4weight: String?, set5weight: String?, set6weight: String?, set7weight: String?,
                                    set1reps: String?,  set2reps: String?,  set3reps: String?,  set4reps: String?,  set5reps: String?,  set6reps: String?,  set7reps: String?,) {
        val set1weightF: Int
        if (set1weight != null) {
            set1weightF = -1
        } else{
            set1weightF = set1weight.toString().toInt()
        }

        val set2weightF: Int
        if (set2weight != null) {
            set2weightF = -1
        } else{
            set2weightF = set2weight.toString().toInt()
        }

        val set3weightF: Int
        if (set3weight != null) {
            set3weightF = -1
        } else{
            set3weightF = set3weight.toString().toInt()
        }

        val set4weightF: Int
        if (set4weight != null) {
            set4weightF = -1
        } else{
            set4weightF = set4weight.toString().toInt()
        }

        val set5weightF: Int
        if (set5weight != null) {
            set5weightF = -1
        } else{
            set5weightF = set5weight.toString().toInt()
        }

        val set6weightF: Int
        if (set6weight != null) {
            set6weightF = -1
        } else{
            set6weightF = set6weight.toString().toInt()
        }

        val set7weightF: Int
        if (set7weight != null) {
            set7weightF = -1
        } else{
            set7weightF = set7weight.toString().toInt()
        }

        val set1repsF: Int
        if (set1reps != null) {
            set1repsF = -1
        } else{
            set1repsF = set1reps.toString().toInt()
        }

        val set2repsF: Int
        if (set2reps != null) {
            set2repsF = -1
        } else{
            set2repsF = set2reps.toString().toInt()
        }

        val set3repsF: Int
        if (set3reps != null) {
            set3repsF = -1
        } else{
            set3repsF = set3reps.toString().toInt()
        }

        val set4repsF: Int
        if (set4reps != null) {
            set4repsF = -1
        } else{
            set4repsF = set5reps.toString().toInt()
        }

        val set5repsF: Int
        if (set5reps != null) {
            set5repsF = -1
        } else{
            set5repsF = set5reps.toString().toInt()
        }

        val set6repsF: Int
        if (set6reps != null) {
            set6repsF = -1
        } else{
            set6repsF = set6reps.toString().toInt()
        }

        val set7repsF: Int
        if (set7reps != null) {
            set7repsF = -1
        } else{
            set7repsF = set7reps.toString().toInt()
        }

        val newExercise = getNewExerciseEntry(exerciseName, workoutId,
            set1weightF, set2weightF, set3weightF, set4weightF, set5weightF, set6weightF, set7weightF,
            set1repsF,  set2repsF,  set3repsF,  set4repsF,  set5repsF,  set6repsF,  set7repsF)
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