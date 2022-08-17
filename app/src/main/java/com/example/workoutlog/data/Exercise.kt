package com.example.workoutlog.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "exerciseName")
    val exerciseName: String,
    @ColumnInfo(name = "workoutId")
    val workoutId: Int,
    @ColumnInfo(name = "set1weight")
    val set1weight: Int? = null,
    @ColumnInfo(name = "set2weight")
    val set2weight: Int? = null,
    @ColumnInfo(name = "set3weight")
    val set3weight: Int? = null,
    @ColumnInfo(name = "set4weight")
    val set4weight: Int? = null,
    @ColumnInfo(name = "set5weight")
    val set5weight: Int? = null,
    @ColumnInfo(name = "set6weight")
    val set6weight: Int? = null,
    @ColumnInfo(name = "set7weight")
    val set7weight: Int? = null,
    @ColumnInfo(name = "set1reps")
    val set1reps: Int? = null,
    @ColumnInfo(name = "set2reps")
    val set2reps: Int? = null,
    @ColumnInfo(name = "set3reps")
    val set3reps: Int? = null,
    @ColumnInfo(name = "set4reps")
    val set4reps: Int? = null,
    @ColumnInfo(name = "set5reps")
    val set5reps: Int? = null,
    @ColumnInfo(name = "set6reps")
    val set6reps: Int? = null,
    @ColumnInfo(name = "set7reps")
    val set7reps: Int? = null,
)
