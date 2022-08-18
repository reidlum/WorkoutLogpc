package com.example.workoutlog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutlog.data.Exercise
import com.example.workoutlog.data.Workout
import com.example.workoutlog.databinding.ExerciseListItemBinding
import com.example.workoutlog.databinding.WorkoutListItemBinding

class ExerciseListAdapter(private val onExerciseClicked: (Exercise) -> Unit) :
    ListAdapter<Exercise, ExerciseListAdapter.ExerciseViewHolder>(ExerciseListAdapter.DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseListAdapter.ExerciseViewHolder {
        val viewHolder = ExerciseViewHolder(
            ExerciseListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onExerciseClicked(getItem(position))
        }
        return viewHolder
    }


    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onExerciseClicked(current)
        }
        holder.bind(current)
    }

    class ExerciseViewHolder(private var binding: ExerciseListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: Exercise) {
            binding.apply {
                itemName.text = exercise.exerciseName
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Exercise>() {
            override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
                return oldItem.exerciseName == newItem.exerciseName
            }
        }
    }

}