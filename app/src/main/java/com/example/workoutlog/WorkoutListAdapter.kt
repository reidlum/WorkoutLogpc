package com.example.workoutlog

import android.content.ClipData
import android.widget.ListAdapter
import com.example.workoutlog.data.Workout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutlog.databinding.WorkoutListItemBinding


class WorkoutListAdapter(private val onWorkoutClicked: (Workout) -> Unit) :
    ListAdapter<Workout, WorkoutListAdapter.WorkoutViewholder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        return WorkoutViewHolder(
            WorkoutListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val current = getWorkout(position)
        holder.itemView.setOnClickListener {
            onWorkoutClicked(current)
        }
        holder.bind(current)
    }

    class WorkoutViewHolder(private var binding: WorkoutListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(workout: Workout) {
            binding.apply {
                itemName.text = workout.workoutName
        }
    }

}