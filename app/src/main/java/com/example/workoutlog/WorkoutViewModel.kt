package com.example.workoutlog
import android.content.ClipData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.workoutlog.data.Workout
import com.example.workoutlog.data.WorkoutDao
import kotlinx.coroutines.launch

/**
 * View Model to keep a reference to the Inventory repository and an up-to-date list of all items.
 *
 */
class InventoryViewModel(private val workoutDao: WorkoutDao) : ViewModel() {

    /**
     * Inserts the new Item into database.
     */
    fun addNewWorkout(workoutName: String) {
        val newWorkout = getNewItemEntry(workoutName)
        insertWorkout(newWorkout)
    }

    /**
     * Launching a new coroutine to insert an item in a non-blocking way
     */
    private fun insertWorkout(workout: Workout) {
        viewModelScope.launch {
            workoutDao.insert(workout)
        }
    }

    /**
     * Returns true if the EditTexts are not empty
     */
    fun isEntryValid(workoutName: String): Boolean {
        if (workoutName.isBlank()) {
            return false
        }
        return true
    }

    /**
     * Returns an instance of the [Item] entity class with the item info entered by the user.
     * This will be used to add a new entry to the Inventory database.
     */
    private fun getNewItemEntry(workoutName: String): Workout {
        return Workout(
            workoutName = workoutName,
        )
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class WorkoutViewModelFactory(private val workoutDao: WorkoutDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(workoutDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}